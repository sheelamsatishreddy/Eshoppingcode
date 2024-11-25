package Framework.TestComponenets;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int trycount = 0;
	
	int maxtry = 1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
	
		
		
		if (trycount<maxtry) {
			trycount++;
			return true;	
		}
		return false;
	}

	
	
	
}
