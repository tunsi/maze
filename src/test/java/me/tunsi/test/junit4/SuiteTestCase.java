package me.tunsi.test.junit4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ GeneralTestCase.class, ParameterizedTestCase.class })
public class SuiteTestCase {

}
