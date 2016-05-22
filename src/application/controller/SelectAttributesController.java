package application.controller;

import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import application.Context;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.core.Attribute;
import weka.core.Instances;

public class SelectAttributesController {

    @FXML
    private Button buttonDeleteAttribute;

    @FXML
    private TextArea textAttributes;

	@FXML
	private void initialize()
	{
	    textAttributes.setEditable(false);
	    textAttributes.setFont(new Font("Courier New", 12));

	    try {
    	    showkAttributes();
    	}
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Najpierw zaladuj plik z danymi.", "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

	public SelectAttributesController()	{}

    private SortedSet<Map.Entry<Attribute,Double>> rankAttributes(Instances instances) throws Exception
    {
        InfoGainAttributeEval eval = new InfoGainAttributeEval();
        eval.buildEvaluator(instances);

        Map<Attribute, Double> attScores = new HashMap<Attribute, Double>();
        Enumeration<Attribute> enumeration = instances.enumerateAttributes();

        int i = 0;
        while (enumeration.hasMoreElements() == true)
        {
            Attribute att = enumeration.nextElement();
            double score  = eval.evaluateAttribute(i++);
            attScores.put(att, score);
        }

        return entriesSortedByValues(attScores, 0);
    }

    private void showkAttributes() throws Exception
    {
        Instances instances = Context.getLoadedInstance();
        SortedSet<Map.Entry<Attribute,Double>> rankedAttributes = rankAttributes(instances);

        String text = String.format("%-13s  %s\n" , "Nazwa_cechy", "Ranking");
        for (Entry<Attribute, Double> entry : rankedAttributes)
        {
            text += String.format("%-13s  %s\n" , entry.getKey().name(), entry.getValue());
        }

        textAttributes.setText(text);
    }

    @FXML
    private void buttonDeleteAttributeClicked() throws Exception
    {
        Instances instances = Context.getLoadedInstance();
        SortedSet<Map.Entry<Attribute,Double>> rankedAttributes = rankAttributes(instances);

        if (rankedAttributes.size() > 1) {
            Attribute lastAttribute = rankedAttributes.last().getKey();

            int index = -1;
            for (int i = 0; i < instances.numAttributes(); i++)
            {
                if (instances.attribute(i).name().equals(lastAttribute.name()))
                {
                    index = i;
                }
            }

            instances.deleteAttributeAt(index);

            showkAttributes();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Nie mozna usunac wiecej cech.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Zrodlo: http://stackoverflow.com/questions/21267988/how-to-rank-features-by-their-importance-in-a-weka-classifier
	/**
	 * Provides a {@code SortedSet} of {@code Map.Entry} objects. The sorting is in ascending order if {@param order} > 0
	 * and descending order if {@param order} <= 0.
	 * @param map   The map to be sorted.
	 * @param order The sorting order (positive means ascending, non-positive means descending).
	 * @param <K>   Keys.
	 * @param <V>   Values need to be {@code Comparable}.
	 * @return      A sorted set of {@code Map.Entry} objects.
	 */
	static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>>
	entriesSortedByValues(Map<K,V> map, final int order) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override
                public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                return (order > 0) ? compareToRetainDuplicates(e1.getValue(), e2.getValue()) : compareToRetainDuplicates(e2.getValue(), e1.getValue());
    	        }
    	    }
	   );
	   sortedEntries.addAll(map.entrySet());
	   return sortedEntries;
	}

	private static <V extends Comparable<? super V>> int compareToRetainDuplicates(V v1, V v2) {
	    return (v1.compareTo(v2) == -1) ? -1 : 1;
	}
}
