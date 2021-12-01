package com.feature.urls;

import java.util.Arrays;
import java.util.HashMap;

import com.feature.urls.IFeature;
import com.feature.urls.PageRankFeatureImpl;
import com.feature.urls.URLContentFeatures;
import com.feature.urls.URLContentModel;
import com.feature.urls.URLFeaturesImpl;
import com.feature.urls.WebsiteAgeFeatureImpl;
import com.feature.urls.WhoisModel;
import com.helper.ConnectionManager;
import com.helper.SpamModel;
import com.helper.StringHelper;
import com.helper.SvmClassifier;
//1. Having IP address
//2. URL Length
//3. URL Shortening service
//4. Having @ symbol
//5. Having double slash
//6. Having dash symbol(Prefix Suffix)
//7. Having multiple subdomains
//8. SSL Final State
//8. Domain Registration Length
//9. Favicon
//11. Request URL
//18. Age of Domain
//20. Web Traffic
//19. DNS Record

public class TestClassifier {
	// url =

	// PHISHING ->//
	// "https://131187547-758969404492316567.preview.editmysite.com/uploads/1/3/1/1/131187547/bankofamerica2020-verification-center-boa-accounts.html ";
	// url="https://pypi.org/project/whois/";
	// http://paypal-identity-form.com/
	// https://app-bill3.com
	// https://forms.gle/P7AKX7GZgjMFTWPe9 => PHishing
	public static void main(String[] args) {
		String url = "http://dinas.tomsk.ru/err/?www.paypal.ch/ch/cgi-bin/webscr1.htm?cmd=_login-run&dispatch=5885d80a13c0db1f1ff80d546411d7f8a8350c132bc41e0934cfc023d4r4ere32132";
		// double isPhishing = checkURLPhishing(url);
		getURLFeatures( url); 
	}

	public static double[] getURLFeatures(String url) {
		// String url = "http://192.168.0.101/t";

		// url =
		// "https://131187547-758969404492316567.preview.editmysite.com/uploads/1/3/1/1/131187547/bankofamerica2020-verification-center-boa-accounts.html ";
		// url="https://pypi.org/project/whois/";
		IFeature<SpamModel, String> urlInfo = new URLFeaturesImpl();
		SpamModel spam = urlInfo.extractFeature(url);
		IFeature<WhoisModel, String> whois = new WebsiteAgeFeatureImpl();
		WhoisModel whoisModel = whois.extractFeature(url);

		IFeature<URLContentModel, String> urlContent = new URLContentFeatures();
		URLContentModel urlContentAttributes = urlContent.extractFeature(url);

		System.out.println("Ranking : " + whoisModel);
		IFeature<Integer, String> obj = new PageRankFeatureImpl();
		int pageRanking = obj.extractFeature(url);
		System.out.println("Ranking : " + pageRanking);
		System.out.println("Short URL: " + spam);
		double[] data = new double[13];
		data = new double[] { 1, -1, -1, 1, 1, 1, -1, 1, 1, 1, 0, 1, 1 };

		// 1st param IP Address in URL
		if (spam.ipAddressURLs > 0) {
			data[0] = -1;
		} else {
			data[0] = 1;
		}

		// 2nd param in URL
		if (url.length() < 54) {
			data[1] = 1;
		} else if (url.length() >= 54 && url.length() <= 75) {
			data[1] = 0;
		} else {
			data[1] = -1;
		}

		// 3rd parameter in url
		if (spam.shortenedURLS > 0) {
			data[2] = -1;
		} else {
			data[2] = 1;
		}

		// 4th parameter in @
		if (url.indexOf("@") != -1) {
			data[3] = -1;
		} else {
			data[3] = 1;
		}

		// 5th parameter double_slash_redirecting
		if (spam.isFwdSlash()) {
			data[4] = -1;
		} else {
			data[4] = 1;
		}

		// 6th parameter prefix_suffix
		if (spam.isDashInDomain()) {
			data[5] = -1;
		} else {
			data[5] = 1;
		}

		// 7th parameter having_sub_domain
		if (spam.getSubDomainsCount() <= 2) {
			data[6] = 1;
		} else if (spam.getSubDomainsCount() == 3) {
			data[6] = 0;
		} else {
			data[6] = -1;
		}

		// 8th parameter domain_registeration_length
		if (!whoisModel.isActive()) {
			data[7] = -1;
		} else {
			if (whoisModel.getExpirationDateDays() < 365) {
				data[7] = -1;
			} else {
				data[7] = 1;
			}
		}
		// 9th parameter faviconFromSameDomain
		if (urlContentAttributes.isFaviconFromSameDomain()) {
			data[8] = 1;
		} else {
			data[8] = -1;
		}
		// 10th parameter requestURLFromSameDomain
		if (urlContentAttributes.getPerSameDomain() < 22) {
			data[9] = 1;
		} else if (urlContentAttributes.getPerSameDomain() >= 22
				&& urlContentAttributes.getPerSameDomain() <= 61) {
			data[9] = 0;
		} else {
			data[9] = -1;
		}
		System.out.println("request_url tc" + data[9]);
		// 11th parameter age_of_domain
		if (!whoisModel.isActive()) {
			data[10] = -1;
		} else {
			if ((whoisModel.getCreationDateDays() + whoisModel
					.getExpirationDateDays()) < 180) {
				data[10] = -1;
			} else {
				data[10] = 1;
			}
		}
		// 12th parameter page ranking
		if (pageRanking > 0 && pageRanking < 100000) {
			data[11] = 1;
		} else if (pageRanking > 100000 && pageRanking < 200000) {
			data[11] = 0;
		} else {
			data[11] = -1;
		}

		// 13th parameter dns record
		if (whoisModel.isActive()) {
			data[12] = 1;
		} else {
			data[12] = -1;
		}

		System.out.println(Arrays.toString(data));
		return data;
	}

	public static HashMap checkURLPhishing(String url) {
		double[] data = getURLFeatures(url);
		System.out.println(Arrays.toString(data));

		// target variable with two levels(1, -1), i.e. whether a website is a
		// phishing website or not.
		// double d=SvmClassifier.getSVMPredication(new
		// double[]{1,0,-1,1,1,-1,1,1});
		// System.out.println(d);
		double d2 = SvmClassifier.getSVMPredication(data);
		System.out.println("Prediction Result" + d2);
		boolean isPhishing = false;
		if (d2 == 0) { // output is -1
			System.out.println("PHISHING");
			isPhishing = true;
		} else {
			System.out.println("NORMAL");
			isPhishing = false;
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("d2", StringHelper.n2s(d2));
		map.put("arr", Arrays.toString(data));
		return map;
	}
}
