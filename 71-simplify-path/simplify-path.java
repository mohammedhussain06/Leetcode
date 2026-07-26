class Solution {
    public String simplifyPath(String path) {

        Deque<String> stack = new ArrayDeque<>();

        String[] parts = path.split("/");

        for (String part : parts) {

            if (part.equals("") || part.equals(".")) {
                continue;
            }

            if (part.equals("..")) {
                if (!stack.isEmpty())
                    stack.removeLast();
            } else {
                stack.addLast(part);
            }
        }

        if (stack.isEmpty())
            return "/";

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append("/");
            sb.append(stack.removeFirst());
        }

        return sb.toString();
    }
}