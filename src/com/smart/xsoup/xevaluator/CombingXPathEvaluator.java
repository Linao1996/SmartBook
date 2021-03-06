package com.smart.xsoup.xevaluator;

import org.jsoup.nodes.Element;

import com.smart.xsoup.XElements;
import com.smart.xsoup.XPathEvaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author code4crafter@gmail.com
 */
public class CombingXPathEvaluator implements XPathEvaluator {

    private List<XPathEvaluator> xPathEvaluators;

    public CombingXPathEvaluator(List<XPathEvaluator> xPathEvaluators) {
        this.xPathEvaluators = xPathEvaluators;
    }

    public CombingXPathEvaluator(XPathEvaluator... xPathEvaluators) {
        this.xPathEvaluators = Arrays.asList(xPathEvaluators);
    }

    
    public XElements evaluate(Element element) {
        List<XElements> xElementses = new ArrayList<XElements>();
        for (XPathEvaluator xPathEvaluator : xPathEvaluators) {
            xElementses.add(xPathEvaluator.evaluate(element));
        }
        return new CombiningDefaultXElements(xElementses);
    }

    
    public boolean hasAttribute() {
        for (XPathEvaluator xPathEvaluator : xPathEvaluators) {
            if (xPathEvaluator.hasAttribute()){
                return true;
            }
        }
        return false;
    }
}
