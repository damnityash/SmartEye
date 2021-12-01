package com.helper;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class SpamModel implements Serializable {
	private static final long SerialVersionUID = 10l;
	public boolean multipleTo = false;
	public int ipAddressURLs = 0;
	public int messageIdInt = 0;
	public int userId = 0;

	public int shortenedURLS = 0;
	public int anchorHrefURLDiffs = 0;
	public boolean isWhitelisted = true;
	public boolean iBlacklisted = true;
	//
	public boolean isFwdSlash = false;
	public boolean isFwdSlash() {
		return isFwdSlash;
	}

	public void setFwdSlash(boolean isFwdSlash) {
		this.isFwdSlash = isFwdSlash;
	}

	public boolean inputTypes = false;

	public boolean isWhitelisted() {
		return isWhitelisted;
	}

	public void setWhitelisted(boolean isWhitelisted) {
		this.isWhitelisted = isWhitelisted;
	}

	public boolean isiBlacklisted() {
		return iBlacklisted;
	}

	public void setiBlacklisted(boolean iBlacklisted) {
		this.iBlacklisted = iBlacklisted;
	}

	public boolean hasSpamKeywords = false;

	public int classifierResult;

	public String protocol;
	public String host;
	public int port;
	public String Athority;
	public String Query;
	public String refrance;
	public int subDomainsCount;
	public int getSubDomainsCount() {
		return subDomainsCount;
	}

	public void setSubDomainsCount(int subDomainsCount) {
		this.subDomainsCount = subDomainsCount;
	}

	public boolean urlAtTheRate;
	public boolean nonStandardPort;
	public boolean dashInDomain;
	public boolean startsWithHttp;

	public boolean isStartsWithHttp() {
		return startsWithHttp;
	}

	public void setStartsWithHttp(boolean startsWithHttp) {
		this.startsWithHttp = startsWithHttp;
	}

	public String param1;
	public String param2;

	boolean isSpam = false;

	public String getProtocol() {
		return protocol;
	}

	public boolean isUrlAtTheRate() {
		return urlAtTheRate;
	}

	public void setUrlAtTheRate(boolean urlAtTheRate) {
		this.urlAtTheRate = urlAtTheRate;
	}

	public boolean isNonStandardPort() {
		return nonStandardPort;
	}

	public void setNonStandardPort(boolean nonStandardPort) {
		this.nonStandardPort = nonStandardPort;
	}

	public boolean isDashInDomain() {
		return dashInDomain;
	}

	public void setDashInDomain(boolean dashInDomain) {
		this.dashInDomain = dashInDomain;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public boolean isSpam() {
		return isSpam;
	}

	public void setSpam(boolean isSpam) {
		this.isSpam = isSpam;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAthority() {
		return Athority;
	}

	public void setAthority(String athority) {
		Athority = athority;
	}

	public String getQuery() {
		return Query;
	}

	public void setQuery(String query) {
		Query = query;
	}

	public String getRefrance() {
		return refrance;
	}

	public void setRefrance(String refrance) {
		this.refrance = refrance;
	}

	public boolean isMultipleTo() {
		return multipleTo;
	}

	public void setMultipleTo(boolean multipleTo) {
		this.multipleTo = multipleTo;
	}

	public int getIpAddressURLs() {
		return ipAddressURLs;
	}

	public void setIpAddressURLs(int ipAddressURLs) {
		this.ipAddressURLs = ipAddressURLs;
	}

	public int getShortenedURLS() {
		return shortenedURLS;
	}

	public void setShortenedURLS(int shortenedURLS) {
		this.shortenedURLS = shortenedURLS;
	}

	public int getAnchorHrefURLDiffs() {
		return anchorHrefURLDiffs;
	}

	public void setAnchorHrefURLDiffs(int anchorHrefURLDiffs) {
		this.anchorHrefURLDiffs = anchorHrefURLDiffs;
	}

	public boolean isInputTypes() {
		return inputTypes;
	}

	public void setInputTypes(boolean inputTypes) {
		this.inputTypes = inputTypes;
	}

	public boolean isHasSpamKeywords() {
		return hasSpamKeywords;
	}

	public void setHasSpamKeywords(boolean hasSpamKeywords) {
		this.hasSpamKeywords = hasSpamKeywords;
	}

	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMessageIdInt() {
		return messageIdInt;
	}

	public void setMessageIdInt(int messageIdInt) {
		this.messageIdInt = messageIdInt;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("SerialVersionUID", SerialVersionUID)
				.add("multipleTo", multipleTo)
				.add("ipAddressURLs", ipAddressURLs)
				.add("messageIdInt", messageIdInt).add("userId", userId)
				.add("shortenedURLS", shortenedURLS)
				.add("anchorHrefURLDiffs", anchorHrefURLDiffs)
				.add("isWhitelisted", isWhitelisted)
				.add("iBlacklisted", iBlacklisted)
				.add("inputTypes", inputTypes)
				.add("hasSpamKeywords", hasSpamKeywords)
				.add("svmResult", classifierResult).add("protocol", protocol)
				.add("host", host).add("port", port).add("Athority", Athority)
				.add("Query", Query).add("refrance", refrance)
				.add("urlAtTheRate", urlAtTheRate)
				.add("nonStandardPort", nonStandardPort)
				.add("dashInDomain", dashInDomain)
				.add("startsWithHttp", startsWithHttp).add("param1", param1)
				.add("param2", param2).add("isSpam", isSpam).add("subDomainsCount", subDomainsCount).toString();
		
	}

	public int getClassifierResult() {
		return classifierResult;
	}

	public void setClassifierResult(int classifierResult) {
		this.classifierResult = classifierResult;
	}

}
