class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter,
                                int x1, int y1, int x2, int y2) {

        // Closest x-coordinate in rectangle to circle center
        int closestX = Math.max(x1, Math.min(xCenter, x2));

        // Closest y-coordinate in rectangle to circle center
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        // Difference between circle center and closest point
        int dx = xCenter - closestX;
        int dy = yCenter - closestY;

        // Compare squared distance with squared radius
        return dx * dx + dy * dy <= radius * radius;
    }
}