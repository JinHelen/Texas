package com.itranswarp.learnjava;

public class Texas {
		//两个执收方
	 	public static int[] black_num = new int[5];
	 	public static int[] white_num = new int[5];
	 	//比较
	    public static String Compare(String black, String white) {
	    	
	        char[] b = black.toCharArray();
	        char[] w = white.toCharArray();
	        //牌号和花色
	        int[] b_num = new int[5];
	        char[] b_color = new char[5];
	        int[] w_num = new int[5];
	        char[] w_color = new char[5];
	        for (int i = 0; i < 5; i++) {
	            b_num[i] = change(b[2 * i]);
	            w_num[i] = change(w[2 * i]);
	            b_color[i] = b[2 * i + 1];
	            w_color[i] = w[2 * i + 1];
	        }
	        setcard(b_num,'b');
	        setcard(b_num,'w');
	        int type_black = getType(b_num, b_color, 'b');
	        int type_white = getType(w_num, w_color,'w');

	        if(type_black>type_white){
	            return "Black wins";
	        }
	        else if(type_black<type_white){
	            return "White wins";
	        }
	        else{
	            return CompareNum(type_black);
	        }
	    }

	    public static int change(char cha){
	        if(cha=='T') return 10;
	        else if(cha=='J') return 11;
	        else if(cha=='Q') return 12;
	        else if(cha=='K') return 13;
	        else if(cha=='A') return 14;
	        else return cha-'0';
	    }
	
	    public static int getType(int[] num, char[] color, char ca) {

	        int[] cardNum = new int[15];
	        for (int i = 0; i < 5; i++) {
	            cardNum[num[i]]++;
	        }
	        int type;
	        boolean Flush = true;
	        for (int i = 1; i < 5; i++) {
	            if (color[i] != color[i - 1]) {
	                Flush = false;
	                break;
	            }
	        }
	        boolean Straight = true;
	        for (int i = 1; i < 5; i++)
	            if (num[i] != num[i - 1] - 1) {
	                Straight = false;
	                break;
	            }
	        //判断牌是否相连或花色同
	        if (Straight || Flush) {
	            if (Straight && Flush) {//同花顺
	                type = 9;
	                setcard(num,ca);
	            } else if (Straight) {//顺子
	                type = 5;
	                setcard(num,ca);
	            }
	            else {//同花
	                type = 6;
	                setcard(num,ca);
	            }
	            return type;
	        }
	        
	        int[] pairs = new int[5];
	        for (int i = 2; i <= 14; i++) {
	            pairs[cardNum[i]]++;
	        }
	        if (pairs[4] != 0)
	        {
	            if (num[0] != num[1]) { // abbbb &&a>b
	                int chan = num[0];
	                num[0] = num[4];
	                num[4] = chan;
	            }
	                setcard(num, ca);
	                type = 8;//铁支
	        } else if (pairs[3] != 0) {
	            int i;
	            for (i = 0; i < 3; i++) {
	                if (num[i] == num[i + 1] && num[i + 1] == num[i + 2]) {
	                    break;
	                }
	            }
	            // i,i+1,i+2
	            int[] buf = new int[5];
	            if (i == 0)
	                setcard(num,ca);
	            else if (i == 1) {
	                buf[0] = num[1];
	                buf[1] = num[2];
	                buf[2] = num[3];
	                buf[3] = num[0];
	                buf[4] = num[4];
	                setcard(buf,ca);
	            } else {
	                buf[0] = num[2];
	                buf[1] = num[3];
	                buf[2] = num[4];
	                buf[3] = num[0];
	                buf[4] = num[1];
	                setcard(buf,ca);
	            }
	            if (pairs[2] != 0)
	                type = 7;//葫芦
	            else
	                type = 4;//三条
	        } else if (pairs[2] != 0) {
	            int[] buf = new int[5];
	            if (pairs[2] == 2) {
	                type = 3;
	                if (num[0] != num[1]) {
	                    buf[0] = num[1];
	                    buf[1] = num[2];
	                    buf[2] = num[3];
	                    buf[3] = num[4];
	                    buf[4] = num[0];
	                    setcard(buf,ca);
	                } else if (num[3] != num[4])//aabbc
	                {
	                    setcard(num,ca);
	                } else// aabcc->aaccb
	                {
	                    int temp = num[2];
	                    num[2] = num[4];
	                    num[4] = temp;
	                    setcard(num,ca);
	                }

	            } else {
	                type = 2;//对子
	                int i;
	                for (i = 0; i < 4; i++) {
	                    if (num[i] == num[i + 1])
	                        break;
	                }
	                buf[0] = num[i];
	                buf[1] = num[i + 1];
	                int c = 2;
	                for (int j = 0; j < i; j++)
	                    buf[c++] = num[j];
	                for (int j = i + 2; j < 5; j++)
	                    buf[c++] = num[j];
	                setcard(buf,ca);
	            }
	        } else //high card
	        {
	            type = 1;//散牌
	            setcard(num,ca);
	        }
	        return type;
	    }

	    public static void setcard(int[] data,char c){
	        int index;
	        if(c=='b') {
	            for (index = 0; index < data.length; index++) {
	            	black_num[index] = data[index];
	            }
	        }
	        else{
	            for (index = 0; index < data.length; index++) {
	            	white_num[index] = data[index];
	            }
	        }
	    }

	    public static String CompareNum(int t) {
	        int j;
	        int maxb = 0, maxw = 0;
	        if (t == 9 || t==5) {
	            for (j = 0; j < 5; j++) {
	                maxb = Math.max(maxb, black_num[j]);
	                maxw = Math.max(maxw, white_num[j]);
	            }
	            if (maxb > maxw) return "Black wins";
	            else if (maxb < maxw) return "White wins";
	            else return "Tie";
	        }
	        else if (t == 8 || t == 7 || t==4) {
	            if (black_num[0] > white_num[0]) return "Black wins";
	            else if (black_num[0] < white_num[0]) return "White wins";
	            else return "Tie";
	        }
	        else if (t == 6 || t == 1) {
	            for(int a=0;a<4;a++){
	                for(int b=0;b<4-a;b++){
	                    if(white_num[b]>white_num[b+1]){
	                        int te = white_num[b];
	                        white_num[b] = white_num[b+1];
	                        white_num[b+1] = te;
	                    }
	                    if(black_num[b]>black_num[b+1]){
	                        int te = black_num[b];
	                        black_num[b] = black_num[b+1];
	                        black_num[b+1] = te;
	                    }
	                }
	            }
	            for(int ind=4;ind>=0;ind--){
	                if(white_num[ind]>black_num[ind]) return "White wins";
	                if(white_num[ind]<black_num[ind]) return "Black wins";
	            }
	            return "Tie";
	        }
	         else if (t == 3) {
	            int[] numb = new int[2];
	            int[] numw = new int[2];
	            int k=1;
	            j=0;
	            int flag=0;
	            while(k<5){
	                if(black_num[j]==black_num[k]){
	                    if(flag==0) {
	                        numb[0] = black_num[j];
	                        j++;
	                        k=j+1;
	                        flag = 1;
	                    }
	                    else{
	                        numb[1] = black_num[j];
	                        break;
	                    }
	                }
	                else{
	                    k++;
	                }
	            }
	            if(k==5){
	                numw[0] = white_num[1];
	                numw[1] = numw[0]==white_num[2]?white_num[3]:white_num[2];
	            }
	            while(k<5){
	                if(white_num[j]==white_num[k]){
	                    if(flag==0) {
	                        numb[0] = white_num[j];
	                        j++;
	                        k=j+1;
	                        flag = 1;
	                    }
	                    else{
	                        numw[1] = white_num[j];
	                        break;
	                    }
	                }
	                else{
	                    k++;
	                }
	            }
	            if(k==5){
	                numw[0] = white_num[1];
	                numw[1] = numw[0]==white_num[2]?white_num[3]:white_num[2];
	            }
	            int data_b=0,data_w=0;
	            if(Math.max(numb[0],numb[1])>Math.max(numw[0],numw[1])){
	                return "Black wins";
	            }
	            else if(Math.max(numb[0],numb[1])<Math.max(numw[0],numw[1])){
	                return "White wins";
	            }
	            else if(Math.min(numb[0],numb[1])>Math.min(numw[0],numw[1])){
	                return "Black wins";
	            }
	            else if(Math.min(numb[0],numb[1])<Math.min(numw[0],numw[1])){
	                return "White wins";
	            }
	            else{
	                for(j=0;j<5;j++){
	                    if(black_num[j]!=numb[0]&&black_num[j]!=numb[1]) data_b = black_num[j];
	                    if(white_num[j]!=numw[0]&&white_num[j]!=numw[1]) data_w = white_num[j];
	                }
	                if(data_b>data_w) return "Black wins";
	                if(data_b<data_w) return "White wins";
	                else return "Tie";
	            }
	        }
	        else{
	            int q=0;
	            int z,y;
	            int tem;
	            int[] b = new int[3];
	            int[] w = new int[3];
	            int m=0,n=0;
	            for(j=0;j<5;j++){
	                m = black_num[j];
	                for(q=0;q<5;q++){
	                    if(m==black_num[q] && m!=q)
	                        break;
	                }
	                if(q<5)
	                    break;
	            }
	            z=0;
	            y=0;
	            while(z<3){
	                if(y!=j && y!=q)
	                    b[z++] = black_num[y++];
	                else
	                    y++;
	            }
	            for(j=0;j<5;j++){
	                n = white_num[j];
	                for(q=0;q<5;q++){
	                    if(n==white_num[q] && n!=q)
	                        break;
	                }
	                if(q<5)
	                    break;
	            }
	            z=0;
	            y=0;
	            while(z<3){
	                if(y!=j && y!=q)
	                    w[z++] = white_num[y++];
	                else
	                    y++;
	            }
	            if(m>n) return "Black wins";
	            else if(m<n) return "White wins";
	            else{
	                for(j=0;j<2;j++){
	                    for(q=0;q<2-j;q++){
	                        if(b[j]>b[j+1]){
	                            tem = b[j];
	                            b[j] = b[j+1];
	                            b[j+1] = tem;
	                        }
	                    }
	                }
	                for(j=0;j<2;j++){
	                    for(q=0;q<2-j;q++){
	                        if(w[j]>w[j+1]){
	                            tem = w[j];
	                            w[j] = w[j+1];
	                            w[j+1] = tem;
	                        }
	                    }
	                }
	                for(j=2;j>=0;j--){
	                    if(b[j]>w[j]) return "Black wins";
	                    if(b[j]<w[j]) return "White wins";
	                }
	                    return "Tie";
	            }
	        }
	    }

}
