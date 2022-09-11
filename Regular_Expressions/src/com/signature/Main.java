package com.signature;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String str = "This is a string";
        System.out.println(str);
        System.out.println(str.replaceAll("T", "I"));

        String alphaNumeric = "abcDEEEF123gHiiiJJkl99z";
        System.out.println(alphaNumeric.replaceAll(".", "X")); //. means anything or any character

        System.out.println(alphaNumeric.replaceAll("^abcDEE", "YYY")); //^ means from starting

        System.out.println(alphaNumeric.matches("^hello")); //matches function matches given string as a whole not char by char // ^ is a boundary matcher
        System.out.println(alphaNumeric.matches("^abcDeee"));
        alphaNumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(alphaNumeric.matches("abcDeeeF12Ghhiiiijkl99z"));

        System.out.println(alphaNumeric.replaceAll("ijkl99z$", "THE END")); // $ means from end or mid to end. //$ is a boundary matcher
        System.out.println(alphaNumeric.replaceAll("[aei]", "X")); // [..] search for given char's
        System.out.println(alphaNumeric.replaceAll("[aei]", "I replaced a letter here"));
        System.out.println(alphaNumeric.replaceAll("[aei][Fj]", "X")); // [..][..] means char followed by another char in another square bracket

        System.out.println("harry".replaceAll("[Hh]arry", "Harry")); // [..]string means given char's followed by given string

        String newAlphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(newAlphanumeric.replaceAll("[^ej]", "X")); // [^..] means exclude given char
        System.out.println(newAlphanumeric.replaceAll("[abcdef345678]", "X"));
        System.out.println(newAlphanumeric.replaceAll("[a-fA-F3-8]", "X")); // [..-.. ..-.. ..-..] means find char within given ranges
        System.out.println(newAlphanumeric.replaceAll("(?i)[a-f3-8]", "X")); // (?i) means case insensitive matching
        System.out.println(newAlphanumeric.replaceAll("[0-9]", "X"));
        System.out.println(newAlphanumeric.replaceAll("\\d", "X")); // find numeric
        System.out.println(newAlphanumeric.replaceAll("\\D", "X")); //find non-numeric

        String hasWhitespace = "I have blanks and\ta tab, and also a newline\n";
        System.out.println(hasWhitespace);
        System.out.println(hasWhitespace.replaceAll("\\s", "")); // \\s means spaces
        System.out.println(hasWhitespace.replaceAll("\t", "X")); // \t means tabs
        System.out.println(hasWhitespace.replaceAll("\\S", "")); // \\S means non-space
        System.out.println(newAlphanumeric.replaceAll("\\w", "X")); // \\w means alphabet, digit and underscore
        System.out.println(hasWhitespace.replaceAll("\\w", "X"));
        System.out.println(hasWhitespace.replaceAll("\\b", "X")); // \\b means surround each string separated by space by given string
        System.out.println(hasWhitespace.replaceAll("\\b", "<b>"));

        String thirdAlphaNumericString = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(thirdAlphaNumericString.replaceAll("^abcDe{3}", "YYYY")); //find for 3e's along with abcD
        System.out.println(thirdAlphaNumericString.replaceAll("^abcDe+", "YYY")); //find for 1 more e's occurrence after abcD
        System.out.println(thirdAlphaNumericString.replaceAll("^abcDe*", "YYYY")); //find for 0 or more e's occurrence after abcD
        System.out.println(thirdAlphaNumericString.replaceAll("^abcDe{2,5}", "YYY")); //find for 2 to 5 occurrence of e after abcD
        System.out.println(thirdAlphaNumericString.replaceAll("h*i+j", "X")); //find for occurrence of 0 or more 'h' 1 or more 'i' and j

        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub Heading</h2>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>This is summary</p>");

        String h2Pattern = "<h2>"/*".*<h2>.*"*/;
        Pattern pattern = Pattern.compile(h2Pattern);
//        Pattern.compile(h2Pattern, Pattern.CASE_INSENSITIVE); for case insensitive pattern.
//        Pattern.compile(h2Pattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE); for case insensitive pattern for unicode characters.
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches()); //matcher.matches() is same as String.matches() and check and matches the given String (char sequence) as a whole
                                               // to solve this problem use regular expression's i.e. - .*String.* -> this means anything before or after the given string which may occur 0 and more times

        matcher.reset();
        while (matcher.find()) {
            System.out.println("Occurrence : " + matcher.start() + " to " + matcher.end());
        }

        // * -> star quantifier is what's called a greedy quantifier so it'll grab as much text as it can.

        // ? -> reluctant or lazy quantifier one that does the minimum amount of work so once it finds a match it doesn't keep
        //looking to see if it can include more characters in the match so it may still find more matches and later parts of the
        //character sequence but each match will contain the minimum number of characters required for match know the ? quantifier
        // means one or zero occurrences is a lazy quantifier so we can use it to turn a greedy quantifier into a lazy quantifier. like (.*?)

//        String h2GroupPattern = "(<h2>.*</h2>)"/*"(<h2>)"*/;
        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());

        while (groupMatcher.find()) {
            System.out.println("Occurrence : " + groupMatcher.group(1));
        }

        String h2TextGroup = "(<h2>)(.*?)(</h2>)";
        Pattern textPattern = Pattern.compile(h2TextGroup);
        Matcher textMatcher = textPattern.matcher(htmlText);
        System.out.println(textMatcher.matches());

        while (textMatcher.find()) {
            System.out.println("Occurrence : " + textMatcher.group(2));
        }

        //use of logical operator in regular expression
        // & operator is used implicitly. e.g :- "abc" = "a" and "b" and "c"
        System.out.println("harry".replaceAll("[h|H]arry", "larry")); // same as harry or Harry
        System.out.println("Harry".replaceAll("[h|H]arry", "larry"));

        //use of not operator.
        //[^abc]
        String tvTest = "tstvtkt";
        String tNotVRegEx = "t(?!v)"; // (?!v) this is a look ahead syntax it always start with (? and end with ), here ! op doesn't consume char .
        // use the not operator which is the ! in a negative look ahead expression now look ahead doesn't consume characters it can actually match nothing, now the not
        //operator the ! here doesn't consume a character so it will match instances of t at the end of a string so if we run this now we should actually get three lines of output
        // when we use a look ahead the characters in the look-ahead aren't part of the match and also note that the indices indicate that our matches are one character in length and if you recall that the end method returns the
        //index after the match so then no longer matching the t followed by another character in other words

        // positive look ahead expression :-
        // t(?=v) means find all matches of T followed by v but we didn't want to include the v in the match

//        String tNotVRegEx = "t[^v]"; // this result only two occurrence because [] consumes char due to which it fails at last t.
//        because when we use the carrot in square
//        brackets regular expression we actually
//        match a character or we must match a
//        character in order for the regular
//        expression to match so we're saying that
//        the t must be followed by any
//        character other than v but in this case there's no character following the final T and so our carrot
//        v in brackets doesn't actually match anything so another way of saying this is that the left square bracket
//        carrot expression right square bracket assumes a character so in other words it requires a character.

        Pattern tvPattern = Pattern.compile(tNotVRegEx);
        Matcher tvMatcher = tvPattern.matcher(tvTest);
        System.out.println(tvMatcher.matches());
        tvMatcher.reset();
        while (tvMatcher.find()) {
            System.out.println("Occurrence : " + tvMatcher.start() + " to " + tvMatcher.end());
        }

        // real world example
        // eg 1:- validate us phone number
        // ^([\(]{1}[0-9]{3}[\)]{1}[ ]{1}[0-9]{3}[\-]{1}[0-9]{4})$

        String phone1 = "1234567890";
        String phone2 = "(123) 456-7890";
        String phone3 = "123 456-7890";
        String phone4 = "(123)456-7890";

        System.out.println("Phone 1 : " + phone1.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("Phone 2 : " + phone2.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("Phone 3 : " + phone3.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));
        System.out.println("Phone 4 : " + phone4.matches("^([\\(]{1}[0-9]{3}[\\)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$"));

        //eg 2:- validate visa card no
        //^4[0-9]{12}([0-9]{3})?$

        String visa1 = "4444444444444";
        String visa2 = "5444444444444";
        String visa3 = "4444444444444444";
        String visa4 = "4444";

        System.out.println("VISA 1 : " + visa1.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("VISA 2 : " + visa2.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("VISA 3 : " + visa3.matches("^4[0-9]{12}([0-9]{3})?$"));
        System.out.println("VISA 4 : " + visa4.matches("^4[0-9]{12}([0-9]{3})?$"));



    }
}
