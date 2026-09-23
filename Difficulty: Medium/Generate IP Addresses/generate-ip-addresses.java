class Solution {
    public ArrayList<String> generateIp(String s) {
        ArrayList<String> ans = new ArrayList<>();

        if (s.length() < 4 || s.length() > 12)
            return ans;

        solve(s, 0, 0, "", ans);
        return ans;
    }

    void solve(String s, int index, int parts, String ip,
               ArrayList<String> ans) {

        // If 4 parts are created
        if (parts == 4) {
            if (index == s.length()) {
                ans.add(ip.substring(0, ip.length() - 1));
            }
            return;
        }

        // Try taking 1, 2, or 3 digits
        for (int len = 1; len <= 3; len++) {

            if (index + len > s.length())
                break;

            String part = s.substring(index, index + len);

            // Leading zero is not allowed
            if (part.length() > 1 && part.charAt(0) == '0')
                continue;

            // Value must be <= 255
            int num = Integer.parseInt(part);
            if (num > 255)
                continue;

            solve(s, index + len, parts + 1,
                  ip + part + ".", ans);
        }
    }
}