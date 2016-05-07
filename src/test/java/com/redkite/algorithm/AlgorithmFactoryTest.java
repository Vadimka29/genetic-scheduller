package com.redkite.algorithm;

import com.redkite.algorithm.impl.AlgorithmFactory;
import org.junit.Test;

/**
 * Created by Vadym on 26.04.2016.
 */

public class AlgorithmFactoryTest {

    @Test
    public void testRetrieveAlgorithmRealization(){
        Algorithm algorithm = AlgorithmFactory.retrieveAlgorithmRealization(AlgorithmType.GENETIC_ALGORITHM);
        algorithm.doCalculation(null, null);
    }
}
