package jkml.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegExService {
	
	public Response evaluate(Request req) {

		int flags = 0;
		
		if (req.caseInsensitive) {
			flags |= Pattern.CASE_INSENSITIVE;
		}
		if (req.multiline) {
			flags |= Pattern.MULTILINE;
		}
		if (req.dotall) {
			flags |= Pattern.DOTALL;
		}
		if (req.unicodeCase) {
			flags |= Pattern.UNICODE_CASE;
		}
		if (req.canonEq) {
			flags |= Pattern.CANON_EQ;
		}
		if (req.unixLines) {
			flags |= Pattern.UNIX_LINES;
		}
		if (req.literal) {
			flags |= Pattern.LITERAL;
		}
		if (req.unicodeCharacterClass) {
			flags |= Pattern.UNICODE_CHARACTER_CLASS;
		}
		if (req.comments) {
			flags |= Pattern.COMMENTS;
		}
		
		Matcher matcher = null;
		Response rsp = new Response();
		
		try {
			matcher = Pattern.compile(req.regex, flags).matcher(req.input);
		}
		catch (PatternSyntaxException pse) {
			rsp.errorMessage = "The expression's syntax is invalid: " + pse.getMessage();
			return rsp;
		}
		catch (IllegalArgumentException iae) {
			rsp.errorMessage = "Bit values other than those corresponding to the defined match flags are set in flags: " + iae.getMessage();
			return rsp;
		}
		
		rsp.groupCount = matcher.groupCount();
		rsp.matches = matcher.matches();
		matcher.reset();
		rsp.find = matcher.find();
		if (!rsp.find && !rsp.matches) {
			return rsp;
		}
		
		int groupCount = matcher.groupCount();
		
		rsp.groups = new Group[groupCount + 1];
		
		for (int i = 0; i <= groupCount; ++i) {
			String content = matcher.group(i);
			int start = matcher.start(i);
			int end = matcher.end(i);
			rsp.groups[i] = new Group();
			if (content != null) {
				rsp.groups[i].content = content;
				rsp.groups[i].start = start;
				rsp.groups[i].end = end;
			}
		}

		return rsp;
	}

}
