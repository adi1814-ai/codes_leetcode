class Node {
    String val;
    Node prev , next;
    Node(String val) {
        this.val = val;
        next = null;
    }
}
class BrowserHistory {
    Node currPage;
    public BrowserHistory(String homepage) {
        currPage = new Node(homepage);
    }
    
    public void visit(String url) {
        Node newPage = new Node(url); // formed a new page
        newPage.prev = currPage;
        currPage.next = newPage;
        currPage = currPage.next;
    }
    
    public String back(int steps) {
        while(currPage.prev != null && steps > 0) {
            currPage = currPage.prev;
            steps--;
        }
        return currPage.val;
    }
    
    public String forward(int steps) {
        while(currPage.next != null && steps > 0) {
            currPage = currPage.next;
            steps--;
        }
        return currPage.val;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */