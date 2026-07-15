class Solution {
    public boolean lemonadeChange(int[] bills) {
       int fivebill =0;
       int tenbill =0;
       for(int bill:bills) {
        if(bill == 5) {
            fivebill++;
        } else if(bill == 10) {
            if(fivebill == 0) return false;
            fivebill--;
            tenbill++;
        } else {
            if(fivebill > 0 && tenbill > 0) {
                fivebill--;
                tenbill--;
            } else if(fivebill >= 3) {
                fivebill -= 3;
            } else {
        
        return false;
       }
    }
    }
       return true;
    }
}