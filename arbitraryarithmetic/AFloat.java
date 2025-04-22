package arbitraryarithmetic;

public class AFloat {
    protected String num;
    public AFloat(){ //Default constructor
        this.num = "0.0";
    }

    public AFloat(String num){
        this.num = num; //parameterized constructor
    }
    public String get(){
        return this.num; // Returns the variable
    }

    public AFloat(AFloat other){ //Copy constructor
        this.num = other.get();
    }

    public static AFloat parse(String num){
        return new AFloat(num); //Static function which returns instance of this class
    }

    public void set(String num){
        this.num = num; //setter function
    }

    public int decimal_digits(){ //Counts the decimal digits
        int decimal_dig=0;
        int start=0;
        for(int i=0;i<this.num.length();i++){
            if(num.charAt(i)=='.'){
                start=1;
            }
            else if(start==1){
                decimal_dig++;
            }
        }
        return decimal_dig;
    }

    public boolean decimal_point(){ //Checks if num has a decimal point
        boolean value = false;
        for(int i=0;i<num.length();i++){
            if(num.charAt(i)=='.'){value=true;break;}
        }
        return value;
    }

    public AFloat add(AFloat other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        int operand_1_decimal_length = this.decimal_digits();
        int operand_2_decimal_length = other.decimal_digits();
        if(!this.decimal_point()){
            operand_1+="."; //If the number doeen't have decimal point then add decimal point
        }
        if(!other.decimal_point()){
            operand_2+=".";
        }
        String fin="";
        if(operand_1_decimal_length==operand_2_decimal_length){
            //Remove the decimal point and add them normally and then insert decimal point at proper position
            String str1="";
            String str2="";
            for(int i=0;i<operand_1.length();i++){
                if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
            }
            for(int i=0;i<operand_2.length();i++){
                if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
            }
            AInteger temp1 = new AInteger(str1);
            AInteger temp2 = new AInteger(str2);
            AInteger res = temp1.add(temp2);
            if(operand_1_decimal_length==0)fin = res.get()+".0";
            else{
                if(res.get().length() > operand_1_decimal_length){
                    if(res.get().charAt(0)!='-'){
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";
                        }
                    }
                    else {
                        if(res.get().length() > 1+operand_1_decimal_length){
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+="0.";
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){
                        fin="0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length();i++){
                            fin+="0";
                        }
                        fin+=res.get();
                    }
                    else{
                        fin="-0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length()+1;i++){
                            fin+="0";
                        }
                        for(int i=1;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                        }
                    }
                }
            }
        }
        else if(operand_1_decimal_length > operand_2_decimal_length){
            //pad some extra zeroes at the end of operand 2 to make decimal lengths equal
            String str1="";
            String str2="";
            for(int i=0;i<operand_1.length();i++){
                if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
            }
            for(int i=0;i<operand_2.length();i++){
                if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
            }
            for(int i=0;i<operand_1_decimal_length-operand_2_decimal_length;i++){
                str2+="0";
            }
            AInteger temp1 = new AInteger(str1);
            AInteger temp2 = new AInteger(str2);
            AInteger res = temp1.add(temp2); //res: result
            if(operand_1_decimal_length==0)fin = res.get()+".0";
            else{
                if(res.get().length() > operand_1_decimal_length){
                    if(res.get().charAt(0)!='-'){
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";
                        }
                    }
                    else {
                        if(res.get().length() > 1+operand_1_decimal_length){
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+="0.";
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){
                        fin="0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length();i++){
                            fin+="0";
                        }
                        fin+=res.get();
                    }
                    else{
                        fin="-0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length()+1;i++){
                            fin+="0";
                        }
                        for(int i=1;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                        }
                    }
                }
            }
        }
        else{
            String str1="";
            String str2="";
            for(int i=0;i<operand_1.length();i++){
                if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
            }
            for(int i=0;i<operand_2.length();i++){
                if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
            }
            for(int i=0;i<operand_2_decimal_length-operand_1_decimal_length;i++){
                str1+="0";
            }
            AInteger temp1 = new AInteger(str1);
            AInteger temp2 = new AInteger(str2);
            AInteger res = temp1.add(temp2);
            if(operand_2_decimal_length==0)fin = res.get()+".0";
            else{
                if(res.get().length() > operand_2_decimal_length){
                    if(res.get().charAt(0)!='-'){
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_2_decimal_length)fin+=".";
                        }
                    }
                    else {
                        if(res.get().length() > 1+operand_2_decimal_length){
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_2_decimal_length)fin+=".";
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_2_decimal_length)fin+="0.";
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){
                        fin="0.";
                        for(int i=0;i<operand_2_decimal_length-res.get().length();i++){
                            fin+="0";
                        }
                        fin+=res.get();
                    }
                    else{
                        fin="-0.";
                        for(int i=0;i<operand_2_decimal_length-res.get().length()+1;i++){
                            fin+="0";
                        }
                        for(int i=1;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                        }
                    }
                }
            }
        }
        String fin_copy="";
        int start=0;
        for(int i=0;i<fin.length();i++){
            if(fin.charAt(i)=='-'){
                fin_copy+="-";
                continue;
            }
            if(start==0){
                if(fin.charAt(i)!='0'){
                    fin_copy+=fin.charAt(i);
                    start=1;
                }
                else if(fin.charAt(i+1)=='.'){
                    fin_copy+=fin.charAt(i);
                    start=1;
                }
            }
            else {
                fin_copy+=fin.charAt(i);
            }
        }
        AFloat result = new AFloat(fin_copy);
        return result;
    }

    public AFloat sub(AFloat other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        int operand_1_decimal_length = this.decimal_digits();
        int operand_2_decimal_length = other.decimal_digits();
        if(!this.decimal_point()){
            operand_1+=".";
        }
        if(!other.decimal_point()){
            operand_2+=".";
        }
        String fin="";
        if(operand_1_decimal_length==operand_2_decimal_length){
            String str1="";
            String str2="";
            for(int i=0;i<operand_1.length();i++){
                if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
            }
            for(int i=0;i<operand_2.length();i++){
                if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
            }
            AInteger temp1 = new AInteger(str1);
            AInteger temp2 = new AInteger(str2);
            AInteger res = temp1.sub(temp2);
            if(operand_1_decimal_length==0)fin = res.get()+".0";
            else{
                if(res.get().length() > operand_1_decimal_length){
                    if(res.get().charAt(0)!='-'){
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";
                        }
                    }
                    else {
                        if(res.get().length() > 1+operand_1_decimal_length){
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+="0.";
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){
                        fin="0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length();i++){
                            fin+="0";
                        }
                        fin+=res.get();
                    }
                    else{
                        fin="-0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length()+1;i++){
                            fin+="0";
                        }
                        for(int i=1;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                        }
                    }
                }
            }
        }
        else if(operand_1_decimal_length > operand_2_decimal_length){
            String str1="";
            String str2="";
            for(int i=0;i<operand_1.length();i++){
                if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
            }
            for(int i=0;i<operand_2.length();i++){
                if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
            }
            for(int i=0;i<operand_1_decimal_length-operand_2_decimal_length;i++){
                str2+="0";
            }
            AInteger temp1 = new AInteger(str1);
            AInteger temp2 = new AInteger(str2);
            AInteger res = temp1.sub(temp2);
            if(operand_1_decimal_length==0)fin = res.get()+".0";
            else{
                if(res.get().length() > operand_1_decimal_length){
                    if(res.get().charAt(0)!='-'){
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";
                        }
                    }
                    else {
                        if(res.get().length() > 1+operand_1_decimal_length){
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+="0.";
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){
                        fin="0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length();i++){
                            fin+="0";
                        }
                        fin+=res.get();
                    }
                    else{
                        fin="-0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length()+1;i++){
                            fin+="0";
                        }
                        for(int i=1;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                        }
                    }
                }
            }
        }
        else{
            String str1="";
            String str2="";
            for(int i=0;i<operand_1.length();i++){
                if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
            }
            for(int i=0;i<operand_2.length();i++){
                if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
            }
            for(int i=0;i<operand_2_decimal_length-operand_1_decimal_length;i++){
                str1+="0";
            }
            AInteger temp1 = new AInteger(str1);
            AInteger temp2 = new AInteger(str2);
            AInteger res = temp1.sub(temp2);
            if(operand_2_decimal_length==0)fin = res.get()+".0";
            else{
                if(res.get().length() > operand_2_decimal_length){
                    if(res.get().charAt(0)!='-'){
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_2_decimal_length)fin+=".";
                        }
                    }
                    else {
                        if(res.get().length() > 1+operand_1_decimal_length){
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_2_decimal_length)fin+=".";
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_2_decimal_length)fin+="0.";
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){
                        fin="0.";
                        for(int i=0;i<operand_2_decimal_length-res.get().length();i++){
                            fin+="0";
                        }
                        fin+=res.get();
                    }
                    else{
                        fin="-0.";
                        for(int i=0;i<operand_2_decimal_length-res.get().length()+1;i++){
                            fin+="0";
                        }
                        for(int i=1;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                        }
                    }
                }
            }
        }
        String fin_copy="";
        int start=0;
        for(int i=0;i<fin.length();i++){
            if(fin.charAt(i)=='-'){
                fin_copy+="-";
                continue;
            }
            if(start==0){
                if(fin.charAt(i)!='0'){
                    fin_copy+=fin.charAt(i);
                    start=1;
                }
                else if(fin.charAt(i+1)=='.'){
                    fin_copy+=fin.charAt(i);
                    start=1;
                }
            }
            else {
                fin_copy+=fin.charAt(i);
            }
        }
        AFloat result = new AFloat(fin_copy);
        return result;
    }

    public AFloat mul(AFloat other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        int operand_1_decimal_length = this.decimal_digits();
        int operand_2_decimal_length = other.decimal_digits();
        if(!this.decimal_point()){
            operand_1+=".";
        }
        if(!other.decimal_point()){
            operand_2+=".";
        }
        String str1 = "";
        String str2 = "";
        for(int i=0;i<operand_1.length();i++){
            if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
        }
        for(int i=0;i<operand_2.length();i++){
            if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
        }
        int start=0;
        String copy_str1="";
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i)=='-'){
                copy_str1+=str1.charAt(i);
                continue;
            }
            if(start==0){
                if(str1.charAt(i)!='0'){
                    copy_str1+=str1.charAt(i);
                    start=1;
                }
                else if(i==str1.length()-1) {
                    copy_str1+=str1.charAt(i);
                    start=1;
                }
            }
            else {
                copy_str1+=str1.charAt(i);
            }
        }
        start=0;
        String copy_str2="";
        for(int i=0;i<str2.length();i++){
            if(str2.charAt(i)=='-'){
                copy_str2+=str2.charAt(i);
                continue;
            }
            if(start==0){
                if(str2.charAt(i)!='0'){
                    copy_str2+=str2.charAt(i);
                    start=1;
                }
                else if(i==str2.length()-1){
                    copy_str2+=str2.charAt(i);
                    start=1;
                }
            }
            else {
                copy_str2+=str2.charAt(i);
            }
        }
        AInteger temp1 = new AInteger(copy_str1);
        AInteger temp2 = new AInteger(copy_str2);
        AInteger res = temp1.mul(temp2);
        String fin="";
        int total_decimal_length = operand_1_decimal_length+operand_2_decimal_length;
        if(res.get().charAt(0)!='-'){
            if(res.get().length()>total_decimal_length){
                for(int i=0;i<res.get().length();i++){
                    fin+=res.get().charAt(i);
                    if(i==res.get().length()-1-total_decimal_length)fin+=".";
                }
                if(total_decimal_length==0)fin+="0";
            }
            else {
                fin="0.";
                for(int i=0;i<total_decimal_length-res.get().length();i++){
                    fin+="0";
                }
                fin+=res.get();
            }
        }
        else {
            if(res.get().length()>1+total_decimal_length){
                for(int i=0;i<res.get().length();i++){
                    fin+=res.get().charAt(i);
                    if(i==res.get().length()-1-total_decimal_length)fin+=".";
                }
                if(total_decimal_length==0)fin+="0";
            }
            else{
                fin+="-0.";
                for(int i=0;i<total_decimal_length-res.get().length()+1;i++){
                    fin+="0";
                }
                for(int i=1;i<res.get().length();i++){
                    fin+=res.get().charAt(i);
                }
            }
        }
        String fin_copy="";
        start=0;
        for(int i=0;i<fin.length();i++){
            if(fin.charAt(i)=='-'){
                fin_copy+="-";
                continue;
            }
            if(start==0){
                if(fin.charAt(i)!='0'){
                    fin_copy+=fin.charAt(i);
                    start=1;
                }
                else if(fin.charAt(i+1)=='.'){
                    fin_copy+=fin.charAt(i);
                    start=1;
                }
            }
            else {
                fin_copy+=fin.charAt(i);
            }
        }
        AFloat result = new AFloat(fin_copy);
        return result;
    }

    public AFloat div(AFloat other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        if(!this.decimal_point()){
            operand_1+=".0";
        }
        if(!other.decimal_point()){
            operand_2+=".0";
        }
        AFloat operand_1_copy = AFloat.parse(operand_1);
        AFloat operand_2_copy = AFloat.parse(operand_2);
        int operand_1_decimal_length = operand_1_copy.decimal_digits();
        int operand_2_decimal_length = operand_2_copy.decimal_digits();
        String str1="";
        String str2="";
        for(int i=0;i<operand_1.length();i++){
            if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
        }
        for(int i=0;i<operand_2.length();i++){
            if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
        }
        int start=0;
        String copy_str1="";
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i)=='-'){
                copy_str1+=str1.charAt(i);
                continue;
            }
            if(start==0){
                if(str1.charAt(i)!='0'){
                    copy_str1+=str1.charAt(i);
                    start=1;
                }
                else if(i==str1.length()-1){
                    copy_str1+=str1.charAt(i);
                    start=1;
                }
            }
            else {
                copy_str1+=str1.charAt(i);
            }
        }
        start=0;
        String copy_str2="";
        for(int i=0;i<str2.length();i++){
            if(str2.charAt(i)=='-'){
                copy_str2+=str2.charAt(i);
                continue;
            }
            if(start==0){
                if(str2.charAt(i)!='0'){
                    copy_str2+=str2.charAt(i);
                    start=1;
                }
                else if(i==str2.length()-1){
                    copy_str2+=str2.charAt(i);
                }
            }
            else {
                copy_str2+=str2.charAt(i);
            }
        }
        if(copy_str2.equals("0")){
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        if(copy_str1.equals("0")){
            AFloat result = new AFloat();
            return result;
        }
        AInteger temp1 = new AInteger(copy_str1);
        AInteger temp2 = new AInteger(copy_str2);
        AInteger quot = temp1.div(temp2);
        int pos=1;
        if(copy_str1.charAt(0)=='-' || copy_str2.charAt(0)=='-'){
            if(copy_str1.charAt(0)=='-' && copy_str2.charAt(0)=='-')pos=1;
            else pos=0;
        }
        AInteger rem = temp1.sub(temp2.mul(quot));
        if(rem.get().charAt(0)=='-'){
            String temp3="";
            for(int i=1;i<rem.get().length();i++)temp3+=rem.get().charAt(i);
            rem.set(temp3);
        }
        if(pos==0 && quot.get().charAt(0)!='-'&& !(rem.get().equals("0"))){
            quot.set("-"+quot.get());
        }
        int diff = operand_2_decimal_length - operand_1_decimal_length;
        String deci="";
        String remainder = rem.get();
        int compare;
        if(1000+diff>0){
            compare=1000+diff;
        }
        else {
            compare=0;
        }
        for(int i=0;i<compare;i++){
            if(remainder.equals("0"))break;
            remainder+="0";
            AInteger temp4 = new AInteger(remainder);
            AInteger quotient = temp4.div(temp2);
            AInteger remain = temp4.sub(temp2.mul(quotient));
            if(rem.get().charAt(0)=='-'){
                String temp3="";
                for(int j=1;i<remain.get().length();j++)temp3+=remain.get().charAt(j);
                remain.set(temp3);
            }
            remainder=remain.get();
            if(quotient.get().charAt(0)=='-'){
                String temp3="";
                for(int j=1;j<quotient.get().length();j++)temp3+=quotient.get().charAt(j);
                quotient.set(temp3);
            }
            deci+=quotient.get();
        }
        String fin="";
        if(diff==0){
        if(!deci.isEmpty())fin = quot.get() + "."+deci;
        else fin = quot.get() + ".0";
        }
        else if(diff>0){
            fin+= quot.get();
            if(diff<deci.length()){
                for(int i=0;i<deci.length();i++){
                    fin+=deci.charAt(i);
                    if(i==diff-1)fin+=".";
                }
            }
            else if(diff==deci.length()){
                for(int i=0;i<deci.length();i++){
                    fin+=deci.charAt(i);
                }
                fin+=".0";
            }
            else {
                if(deci.isEmpty()){
                    for(int i=0;i<diff;i++){
                        fin+="0";
                    }
                    fin+=".0";
                }
                else {
                    for(int i=0;i<deci.length();i++){
                        fin+=deci.charAt(i);
                    }
                    for(int i=deci.length();i<diff;i++){
                        fin+="0";
                    }
                    fin+=".0";
                }
            }
        }
        else {
            int mod_diff=-1*diff;
            if(quot.get().charAt(0)!='-'){
                if(mod_diff<quot.get().length()){
                    for(int i=0;i<quot.get().length();i++){
                        fin+=quot.get().charAt(i);
                        if(i==quot.get().length()-1-mod_diff)fin+=".";
                    }
                    fin+=deci;
                }
                else if(mod_diff==quot.get().length()){
                    fin+="0."+quot.get()+deci;
                }
                else {
                    fin+="0.";
                    for(int i=0;i<mod_diff-quot.get().length();i++){
                        fin+="0";
                    }
                    fin+=quot.get()+deci;
                }
            }
            else {
                if(mod_diff < quot.get().length()-1){
                    for(int i=0;i<quot.get().length();i++){
                        fin+=quot.get().charAt(i);
                        if(i==quot.get().length()-1-mod_diff)fin+=".";
                    }
                    fin+=deci;
                }
                else if(mod_diff==quot.get().length()-1){
                    fin+="-0.";
                    for(int i=1;i<quot.get().length();i++){
                        fin+=quot.get().charAt(i);
                    }
                    fin+=deci;
                }
                else {
                    fin+="-0.";
                    for(int i=0;i<mod_diff-quot.get().length()+1;i++){
                        fin+="0";
                    }
                    for(int i=1;i<quot.get().length();i++){
                        fin+=quot.get().charAt(i);
                    }
                    fin+=deci;
                }
            }
        }
        start=0;
        String updated_fin="";
        for(int i=0;i<fin.length();i++){
            if(fin.charAt(i)=='-'){
                updated_fin+=fin.charAt(i);
                continue;
            }
            if(start==0){
                if(fin.charAt(i)!='0'){
                    updated_fin+=fin.charAt(i);
                    start=1;
                }
                else if(fin.charAt(i+1)=='.'){
                    updated_fin+=fin.charAt(i);
                    start=1;
                }
            }
            else {
                updated_fin+=fin.charAt(i);
            }
        }
        AFloat result = new AFloat(updated_fin);
        return result;
    }
}