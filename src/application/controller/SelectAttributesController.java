package application.controller;

import java.util.Comparator;
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
    private Button buttonAddAttribute;

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
	        SortedSet<Map.Entry<Attribute,Double>> rankedAttributes = rankAttributes();
    	    showkAttributes(rankedAttributes);
    	}
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Najpierw zaladuj plik z danymi.", "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

	public SelectAttributesController()	{}

    private SortedSet<Map.Entry<Attribute,Double>> rankAttributes() throws Exception
    {
        Instances instances = Context.getCurrentInstance();

        InfoGainAttributeEval eval = new InfoGainAttributeEval();
        eval.buildEvaluator(instances);

        Map<Attribute, Double> attScores = new HashMap<Attribute, Double>();
        for (int i = 0; i < instances.numAttributes(); i++)
        {
            Attribute att = instances.attribute(i);
            double score  = eval.evaluateAttribute(i);
            attScores.put(att, score);
        }

        return entriesSortedByValues(attScores, 0);
    }

    private void showkAttributes(SortedSet<Map.Entry<Attribute,Double>> rankedAttributes) throws Exception
    {
        String text = "";
        for (Entry<Attribute, Double> entry : rankedAttributes)
        {
            text += String.format("%-13s= %s\n" , entry.getKey().name(), entry.getValue());
        }

        textAttributes.setText(text);
    }

	@FXML
    private void buttonAddAttributeClicked()
    {

    }

    @FXML
    private void buttonDeleteAttributeClicked()
    {

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
