import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

class Pattern {
	private String pattern;
	private String[] strs;
	private boolean isMatched = false;
	private Character[] patternTypes;
	private String[] strTypes;
	private LinkedHashMap<Character, String> map;

	public static Pattern createAndVerify(String pattern, String[] strs) {
		var p = new Pattern(pattern, strs);
		p.verify();
		return p;
	}

	private Pattern(String pattern, String[] strs) {
		this.pattern = pattern;
		this.strs = strs;
	}

	private void verify() {
		// check if strs's length is matched to pattern's length
		if (this.pattern.length() != this.strs.length) {
			return;
		}

		// check if strs's type is matched to pattern's type
		var pset = new LinkedHashSet<Character>();
		for (char c : this.pattern.toCharArray()) {
			pset.add(c);
		}
		this.patternTypes = pset.toArray(new Character[0]);
		var sset = new LinkedHashSet<String>();
		for (String s : this.strs) {
			sset.add(s);
		}
		this.strTypes = sset.toArray(new String[0]);
		if (this.patternTypes.length != this.strTypes.length) {
			return;
		}

		// create map of pattern and strings
		this.map = new LinkedHashMap<Character, String>();
		for (int idx = 0; idx < this.patternTypes.length; idx++) {
			this.map.put(this.patternTypes[idx], this.strTypes[idx]);
		}

		// compare pattern and strings
		for (int idx = 0; idx < this.strs.length; idx++) {
			char c = this.pattern.charAt(idx);
			String s = this.strs[idx];
			if (!this.map.get(c).equals(s)) {
				return;
			}
		}

		this.isMatched = true;
	}

	public boolean getIsMatched() {
		return this.isMatched;
	}

	public void printAsTree() {
		var chs = this.getSortedCharArray();
		var patternTypesCnt = new LinkedHashMap<Character, Integer>();
		for (char ch : chs) {
			if (patternTypesCnt.containsKey(ch)) {
				int v = patternTypesCnt.get(ch);
				patternTypesCnt.put(ch, v + 1);
			} else {
				patternTypesCnt.put(ch, 1);
			}
		}

		var trees = new ArrayList<LinkedHashMap<Character, Integer>>();
		while (!patternTypesCnt.isEmpty()) {
			int lastCnt = 0;
			var m = new LinkedHashMap<Character, Integer>();
			trees.add(m);
			for (Map.Entry<Character, Integer> entry : patternTypesCnt.entrySet()) {
				int max = (int) Math.pow(2, lastCnt);
				if (entry.getValue() > max) {
					m.put(entry.getKey(), max);
					lastCnt = max;
					entry.setValue(entry.getValue() - max);
				} else {
					m.put(entry.getKey(), entry.getValue());
					lastCnt = entry.getValue();
					entry.setValue(0);
				}
			}
			patternTypesCnt.entrySet().removeIf(e -> e.getValue() == 0);
		}

		var content = new ArrayList<ArrayList<String>>();
		for (var tree : trees) {
			var reverseTreePattern = tree.keySet().toArray(new Character[0]);
			var lines = new ArrayList<String>();
			int layer = 0;
			content.add(lines);
			for (int idx = reverseTreePattern.length - 1; idx >= 0; idx--) {
				String str = map.get(reverseTreePattern[idx]);
				int n = tree.get(reverseTreePattern[idx]);
				String spaceStart = " ".repeat(layer);
				String spaceBetween = " ".repeat(layer * 2 + 1);
				String line = spaceStart + str + (spaceBetween + str).repeat(n - 1);
				lines.add(line);
				layer++;
			}
		}

		for (var lines : content) {
			for (int idx = lines.size() - 1; idx >= 0; idx--) {
				System.out.println(lines.get(idx));
			}
			System.out.println("--------------------");
		}
	}

	private char[] getSortedCharArray() {
		// sort pattern
		char[] sortedPattern = this.pattern.toCharArray();
		Arrays.sort(sortedPattern);
		return sortedPattern;
	}
}
