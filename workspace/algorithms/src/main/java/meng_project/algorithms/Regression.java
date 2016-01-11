package meng_project.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Regression { // needs re-work

	double x;
	TimeProcessor tp = new TimeProcessor();

	public double[] timeArray;
	public double[] obsArray;

	public List<Double> convertToDouble(List<String> stringList) { // make these
																	// more
																	// clear

		List<Double> listYDouble = new ArrayList<Double>();

		for (Iterator<String> iter = stringList.iterator(); iter.hasNext();) {

			listYDouble.add(Double.parseDouble(iter.next()));
		}
		return listYDouble;

	}

	public double[] convertToArray(List<Double> doubleList) {

		double[] arr = doubleList.stream().mapToDouble(Double::doubleValue).toArray();

		return arr;

	}

	public void dailyRegression() { // get hourly values build regression with
									// simple regression

	}

	/**
	 * 
	 * @param xArray
	 * @param yArray
	 * @return pearsons correlation coefficient
	 */
	public double correlation(final double[] xArray, final double[] yArray) {
		SimpleRegression regression = new SimpleRegression();
		if (xArray.length != yArray.length) {
			throw new DimensionMismatchException(xArray.length, yArray.length);
		} else if (xArray.length < 2) {
			throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, xArray.length, 2);
		} else {
			for (int i = 0; i < xArray.length; i++) {
				regression.addData(xArray[i], yArray[i]);
			}
			return regression.getR();
		}
	}

	public SimpleRegression getRegression(double[] xs, double[] ys, boolean intercept) {
		if (xs.length != ys.length) {
			throw new DimensionMismatchException(xs.length, ys.length);
		} else {
			SimpleRegression sr = new SimpleRegression(intercept);
			for (int i = 0; i < xs.length; i++) {
				sr.addData(xs[i], ys[i]);
			}
			return sr;
		}

	}

	// create map of a list

	public SimpleRegression regressForDayOfWeek(int day) {
		DataProcessor dp = new DataProcessor();
		dp.readData(day, false, true, false);
		List<Double> timeList = tp.timeListToDouble(dp.averageDayList);
		this.timeArray = convertToArray(timeList);
		this.obsArray = convertToArray(dp.averageList);
		SimpleRegression regress = getRegression(this.timeArray, this.obsArray, false);

		return regress;
	}

	public SimpleRegression regressionForHour(int hour, int minute) {
		TimeProcessor tp = new TimeProcessor();
		tp.setTime(hour, minute);
		DataProcessor dp = new DataProcessor();
		dp.readData(3, false, false, true);
		List<Double> timeList = tp.timeListToDouble(dp.timeListTime);
		this.timeArray = convertToArray(timeList);
		this.obsArray = convertToArray(dp.obsListTime);

		SimpleRegression regress = getRegression(this.timeArray, this.obsArray, false);

		return regress;
	}

	public SimpleRegression regressForToday() {

		DataProcessor dpi = new DataProcessor();
		dpi.readData(3, true, false, false);
		List<Double> timeList = tp.timeListToDouble(dpi.timeList);
		List<Double> obsList = convertToDouble(dpi.obsListY);
		this.timeArray = convertToArray(timeList);
		this.obsArray = convertToArray(obsList);
		SimpleRegression regress = getRegression(this.timeArray, this.obsArray, false);

		return regress;
	}

}
