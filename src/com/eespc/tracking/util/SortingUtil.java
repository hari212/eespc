package com.eespc.tracking.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SortingUtil {

	private List listToSort;
	private long startTime;
	private Class objectNameInList;
	private Method methodNameToInvoke;
	private Method secondaryMethodNameToInvoke;
	private Class params[];
	private Object paramsForMethod[];
	private boolean isStringComparison;
	private boolean isSecondaryStringComparison;
	private boolean secondarySort;
	private static Log log;
	static Class class$2; /* synthetic field */

	public SortingUtil() {
		startTime = System.currentTimeMillis();
		objectNameInList = null;
		methodNameToInvoke = null;
		secondaryMethodNameToInvoke = null;
		params = new Class[0];
		paramsForMethod = new Object[0];
		isStringComparison = false;
		isSecondaryStringComparison = false;
		secondarySort = false;
	}

	public void sort(List _listToSort, Class objectNameInList,
			String memberToSort, boolean isAscending) throws Exception {
		if (_listToSort == null || _listToSort != null
				&& _listToSort.size() == 0) {
			log.info("List to sort is empty. So returning...");
			return;
		}
		if (objectNameInList == null || memberToSort == null
				|| memberToSort != null && memberToSort.trim().length() == 0) {
			throw new Exception("Mandatory Parameters are not Passed.");
		}
		startTime = System.currentTimeMillis();
		listToSort = _listToSort;
		if (isAscending) {
			try {
				methodNameToInvoke = objectNameInList.getMethod(memberToSort,
						params);
				Class methodRetrunType = methodNameToInvoke.getReturnType();
				if (methodRetrunType == java.lang.String.class) {
					isStringComparison = true;
				} else if (methodRetrunType == Integer.TYPE) {
					isStringComparison = false;
				}
			} catch (NoSuchMethodException e) {
				log.error("In Single Sorting method", e);
			}
			if (listToSort.size() > 1) {
				sortMeQuick(0, listToSort.size() - 1);
			}
		} else {
			List reverseList = new Vector();
			for (int i = listToSort.size() - 1; i >= 0; i--) {
				reverseList.add(listToSort.get(i));
			}

			listToSort.removeAll(listToSort);
			listToSort.addAll(reverseList);
		}
		log.debug("Time Taken for Sorting ="
				+ (System.currentTimeMillis() - startTime));
	}

	public void sort(List _listToSort, Class objectNameInList,
			String memberToSort, String secondaryMemberToSort,
			boolean isAscending) throws Exception {
		if (_listToSort == null || _listToSort != null
				&& _listToSort.size() == 0) {
			log.info("List to sort is empty. So returning...");
			return;
		}
		if (objectNameInList == null || memberToSort == null
				|| memberToSort != null && memberToSort.trim().length() == 0) {
			throw new Exception("Mandatory Parameters are not Passed.");
		}
		secondarySort = true;
		if (secondaryMemberToSort == null) {
			sort(_listToSort, objectNameInList, memberToSort, isAscending);
			return;
		}
		startTime = System.currentTimeMillis();
		listToSort = _listToSort;
		if (isAscending) {
			try {
				methodNameToInvoke = objectNameInList.getMethod(memberToSort,
						params);
				Class methodRetrunType = methodNameToInvoke.getReturnType();
				if (methodRetrunType == java.lang.String.class) {
					isStringComparison = true;
				} else if (methodRetrunType == Integer.TYPE) {
					isStringComparison = false;
				}
				secondaryMethodNameToInvoke = objectNameInList.getMethod(
						secondaryMemberToSort, params);
				Class secondaryMethodRetrunType = secondaryMethodNameToInvoke
						.getReturnType();
				if (secondaryMethodRetrunType == java.lang.String.class) {
					isSecondaryStringComparison = true;
				} else if (secondaryMethodRetrunType == Integer.TYPE) {
					isSecondaryStringComparison = false;
				}
			} catch (NoSuchMethodException e) {
				log.error("In Secondary Sorting method", e);
			}
			if (listToSort.size() > 1) {
				sortMeQuick(0, listToSort.size() - 1);
			}
		} else {
			List reverseList = new Vector();
			for (int i = listToSort.size() - 1; i >= 0; i--) {
				reverseList.add(listToSort.get(i));
			}

			listToSort.removeAll(listToSort);
			listToSort.addAll(reverseList);
		}
		log.debug("Time Taken for Sorting ="
				+ (System.currentTimeMillis() - startTime));
	}

	private void sortMeQuick(int p, int r) throws Exception {
		if (p < r) {
			int q = quickPartion(p, r);
			sortMeQuick(p, q);
			sortMeQuick(q + 1, r);
		}
	}

	private int quickPartion(int left, int right) throws Exception {
		Object o1 = listToSort.get(left);
		int i = left - 1;
		int j = right + 1;
		do {
			do {
				j--;
			} while (greaterThan(listToSort.get(j), o1));
			do {
				i++;
			} while (lessThan(listToSort.get(i), o1));
			if (i < j) {
				swap(i, j);
			} else {
				return j;
			}
		} while (true);
	}

	private void swap(int loc1, int loc2) {
		Object tmp = listToSort.get(loc1);
		listToSort.set(loc1, listToSort.get(loc2));
		listToSort.set(loc2, tmp);
	}

	private boolean greaterThan(Object lhs, Object rhs) throws Exception {
		String left;
		String right;
		Integer left1;
		Integer right1;
		if (isStringComparison) {
			left = (String) methodNameToInvoke.invoke(lhs, paramsForMethod);
			right = (String) methodNameToInvoke.invoke(rhs, paramsForMethod);
			if (secondarySort && left != null && right != null
					&& left.equalsIgnoreCase(right)) {
				return isSecondaryGreaterThan(lhs, rhs);
			}
			int value = left.toUpperCase().compareTo(right.toUpperCase());
			return value > 0;
		}

		left1 = (Integer) methodNameToInvoke.invoke(lhs, paramsForMethod);
		right1 = (Integer) methodNameToInvoke.invoke(rhs, paramsForMethod);
		if (secondarySort && left1 == right1) {
			return isSecondaryGreaterThan(lhs, rhs);
		}
		return left1.intValue() > right1.intValue();
	}

	private boolean lessThan(Object lhs, Object rhs) throws Exception {
		String left;
		String right;
		Integer left1;
		Integer right1;
		if (isStringComparison) {
			left = (String) methodNameToInvoke.invoke(lhs, paramsForMethod);
			right = (String) methodNameToInvoke.invoke(rhs, paramsForMethod);
			if (secondarySort && left != null && right != null
					&& left.equalsIgnoreCase(right)) {
				return isSecondaryLessThan(lhs, rhs);
			}
			int value = left.toUpperCase().compareTo(right.toUpperCase());
			return value < 0;
		}
		left1 = (Integer) methodNameToInvoke.invoke(lhs, paramsForMethod);
		right1 = (Integer) methodNameToInvoke.invoke(rhs, paramsForMethod);
		if (secondarySort && left1 == right1) {
			return isSecondaryLessThan(lhs, rhs);
		}
		return left1.intValue() < right1.intValue();
	}

	private boolean isSecondaryGreaterThan(Object lhs, Object rhs)
			throws Exception {
		String left;
		String right;
		Integer left1;
		Integer right1;
		if (isSecondaryStringComparison) {
			left = (String) secondaryMethodNameToInvoke.invoke(lhs,
					paramsForMethod);
			right = (String) secondaryMethodNameToInvoke.invoke(rhs,
					paramsForMethod);
			int value = left.toUpperCase().compareTo(right.toUpperCase());
			return value > 0;
		}
		left1 = (Integer) secondaryMethodNameToInvoke.invoke(lhs,
				paramsForMethod);
		right1 = (Integer) secondaryMethodNameToInvoke.invoke(rhs,
				paramsForMethod);
		return left1.intValue() > right1.intValue();
	}

	private boolean isSecondaryLessThan(Object lhs, Object rhs)
			throws Exception {
		String left;
		String right;
		Integer left1;
		Integer right1;
		if (isSecondaryStringComparison) {
			left = (String) secondaryMethodNameToInvoke.invoke(lhs,
					paramsForMethod);
			right = (String) secondaryMethodNameToInvoke.invoke(rhs,
					paramsForMethod);
			int value = left.toUpperCase().compareTo(right.toUpperCase());
			return value < 0;
		}
		left1 = (Integer) secondaryMethodNameToInvoke.invoke(lhs,
				paramsForMethod);
		right1 = (Integer) secondaryMethodNameToInvoke.invoke(rhs,
				paramsForMethod);
		return left1.intValue() < right1.intValue();
	}

	public static void main(String args1[]) {
	}

	static {
		log = LogFactory.getLog(com.eespc.tracking.util.SortingUtil.class);
	}
}
