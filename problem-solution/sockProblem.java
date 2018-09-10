public class Socks {
    public static void main(String[] args) {
        String originalSocksString = "1/blue/right,2/blue/right,3/red/right,4/blue/left,5/purple/left,6/red/left,7/green/left,8/red/left,9/blue/left";
        String[] sockPair = originalSocksString.split(",");

        // Move all the right sock to one list
        List<String> rightSocks = new ArrayList<>();
        for (String sock : sockPair) {
            if (sock.contains("right")) {
                rightSocks.add(sock);
            }
        }

        // move all the left sock to one list
        List<String> leftSocks = new ArrayList<>();
        for (String sock : sockPair) {
            if (sock.contains("left")) {
                leftSocks.add(sock);
            }
        }

        List<String> side1 = new ArrayList<>();
        List<String> side2 = new ArrayList<>();
        if (rightSocks.size() <= leftSocks.size()) {
            side1.addAll(rightSocks);
            side2.addAll(leftSocks);
        } else {
            side1.addAll(leftSocks);
            side2.addAll(rightSocks);
        }

        for (int i = 0; i < side1.size(); i++) {
            String[] rightSockIdColor = side1.get(i).split("/");
            String rightId = rightSockIdColor[0];
            String rightColor = rightSockIdColor[1];

            for (int j = 0; j < side2.size(); j++) {

                String[] leftSockIdColor = side2.get(j).split("/");
                String leftId = leftSockIdColor[0];
                String leftColor = leftSockIdColor[1];

                if (rightColor.equals(leftColor)) {
                    System.out.println("\n Expected Output : " + rightId + leftId);
                    side2.remove(j);
                    break;
                }
            }
        }
    }
}
