package meng_project.algorithms;

import java.io.FileReader;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.json.simple.parser.JSONParser;

public class Main {
	
	
	static Object obj ;
	
	
	public static void main(String[] args) {
		try {
			JSONParser parser = new JSONParser();
			Object obj1 = parser
					.parse(new FileReader("C:\\Users\\Utku\\Desktop\\EEE\\MEngProject\\Datasets\\data.json"));
			obj = obj1;
			
		
			Regression reg = new Regression();
			Regression regT = new Regression();
			Regression rh = new Regression();
			SimpleRegression regresshour = rh.regressionForHour(00, 00);
			SimpleRegression regress = reg.regressForDayOfWeek(3);
			SimpleRegression rt = regT.regressForToday();
			
//			regress.getIntercept()
//			System.out.println(reg.obsArray[0]+"-" +reg.obsArray[1]+"-" +reg.obsArray[2]+"-" +reg.obsArray[3]);
//			System.out.println(reg.timeArray[0]+"-" +reg.timeArray[1]+"-" +reg.timeArray[2]+"-" +reg.timeArray[3]);
//			System.out.println(regT.obsArray[40] +" OBS FOR DAY");
//			System.out.println(regT.timeArray[40] + " TIME FOR DAY");
			System.out.println(rh.obsArray.length);
			System.out.println(rh.timeArray.length);
			System.out.println(rh.timeArray[17] +" tiiiime");
			System.out.println(rh.obsArray[19]+ "oooobs");
			System.out.println(regresshour.predict(rh.timeArray[19]) + "predict hour");
			System.out.println(rt.predict(regT.timeArray[40] ));
			System.out.println(rt.getMeanSquareError());
			//			dp.readData(3,false,true);
//			//
//			//
//			// //these methods have to move
//			 System.out.println(dp.averageList.size());
//			 System.out.println(dp.averageDayList.size() +"<<<<<<<<<<");
//			 List<Double> exlist1 = new ArrayList();
//			 List<Double> exlist = new ArrayList();
//			
//			 exlist = tp.timeListToDouble(dp.timeList);
//			 exlist1 = reg.convertToDouble(dp.obsListY);
//			
//			 System.out.println(exlist.get(0));
//			// System.out.println(exlistTime.get(0));
//			 System.out.println(tp.doubleToString(exlist.get(0)));
//			 //
//			 double[] timeArray = reg.convertToArray(exlist);
//			 double[] energyArray = reg.convertToArray(exlist1);
//			//
//			 System.out.println(timeArray[0] +"time value");
//			 System.out.println(energyArray[0] +" energy value");
//			
//			//
//			 SimpleRegression regresss= reg.getRegression(timeArray, energyArray);
//			 regresss.regress();
//			 System.out.println(regresss.getMeanSquareError() +"asda"  +regresss.predict(tp.timeToDouble("15/01/2015-11:00")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
