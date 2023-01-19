package TeamLongestPeriod;

public class Pair {
    private String firstEmployeeID;
    private String secondEmployeeID;
    private int totalOverlap;

    public Pair(String firstEmployeeID, String secondEmployeeID, int totalOverlap) {
        this.firstEmployeeID = firstEmployeeID;
        this.secondEmployeeID = secondEmployeeID;
        this.totalOverlap = totalOverlap;
    }
    public String getFirstEmployeeID() {
        return firstEmployeeID;
    }

    public String getSecondEmployeeID() {
        return secondEmployeeID;
    }

    public int getTotalOverlap() {
        return totalOverlap;
    }

    public void addOverlapTime(int time) {
        totalOverlap += time;
    }

    @Override
    public String toString() {
        return firstEmployeeID + ", " + secondEmployeeID + ", " + totalOverlap;
    }
}
