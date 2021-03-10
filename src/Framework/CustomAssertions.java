package Framework;



public class CustomAssertions {
	
	
	
	public static void assertTrue(boolean arg0) throws RuntimeException {
		try {
			org.testng.Assert.assertTrue(arg0);
			
		}
		//we don't want to catch both exception and error in our test so we convert the Error into checked Exception
		catch(Error e) {
			throw new RuntimeException("Checked condition is False");
			
		}
		
	}
	
	public static void assertTrue(boolean arg0,String arg1) throws RuntimeException {
		try {
			org.testng.Assert.assertTrue(arg0,arg1);
			
		}
		//we don't want to catch both exception and error in our test so we convert the Error into checked Exception
		catch(Error e) {
			throw new RuntimeException(arg0 + "is not true");
			
		}
		
	}

	}