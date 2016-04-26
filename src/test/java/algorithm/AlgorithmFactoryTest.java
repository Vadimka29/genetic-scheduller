package algorithm;

import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.AlgorithmFactory;
import com.redkite.algorithm.model.AlgorithmType;
import org.junit.Test;

/**
 * Created by Vadym on 26.04.2016.
 */

public class AlgorithmFactoryTest {

    @Test
    public void testRetrieveAlgorithmRealization(){
        Algorithm algorithm =  (new AlgorithmFactory()).retrieveAlgorithmRealization(AlgorithmType.GENETIC_ALGORITHM);
        algorithm.doCalculation();
    }
}
