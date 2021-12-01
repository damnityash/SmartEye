package com.constant;

import com.helper.SvmClassifier;

public class ServerConstants {
	public static final String db_driver = "com.mysql.jdbc.Driver";
	public static final String db_user = "root";
	public static final String db_pwd = "";
	public static final String db_url = "jdbc:mysql://localhost/antiphishing";

	public static final SvmClassifier svm = new SvmClassifier();
	public static final String PROJECT_DATASET_DIR = "D:\\work\\project\\AntiPhishingWeb\\AntiPhishingWeb\\";
	
	
	public static final String PARSING_FILE="";
	public static final String NEURAL_DIR = PROJECT_DATASET_DIR + "\\neural\\";
	public static final String NEURAL_MODEL_FILE = NEURAL_DIR + "neural.model";
	public static final String NEURAL_TRAINING_SET = NEURAL_DIR + "dataset.arff";
	public static final String APPLICATION_NAME = "AntiPhishingWeb";

}
