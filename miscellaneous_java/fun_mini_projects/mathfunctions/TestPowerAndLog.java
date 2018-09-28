package mathfunctions;

import java.util.Date;

public class TestPowerAndLog {

	public static void main(String[] args) {
		try {
//			double ans = PowerAndLog.logMidp(Math.E * Math.E * Math.E * Math.E, delta);
			Date d1 = new Date();
			double ans = PowerAndLog.eToThe(5, 0.00001, 0.00001);
			Date d2 = new Date();
			long time = d2.getTime() - d1.getTime();
			
			System.out.println(ans + "\n" + time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
//for ln(e)     0.00000001 is best  (2.7)
//for ln(e^2)   0.0000001 is best   (7.4)
//for ln(e^3)   0.0000001 is best   (20)
//for ln(e^4)   0.00001 is best     (55)
//for ln(10)    0.00001 is best
//for ln(100)   0.0001 is best
//for ln(1000)  0.000001 is best
//for ln(10000) 0.0001 is best


