package jkml.regex;

public class Request {
	
	boolean caseInsensitive = false;
	boolean multiline = false;
	boolean dotall = false;
	boolean unicodeCase = false;
	boolean canonEq = false;
	boolean unixLines = false;
	boolean literal = false;
	boolean unicodeCharacterClass = false;
	boolean comments = false;
	
	String regex;
	String input;
	
	public String getRegex() {
		return regex;
	}
	
	public void setRegex(String regex) {
		this.regex = regex;
	}
	
	public String getInput() {
		return input;
	}
	
	public void setInput(String input) {
		this.input = input;
	}

}
