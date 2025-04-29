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

    public String remove_extra_zeroes(String str){ //function which removes extra zeroes at the end of decimal places.
        AFloat temp = AFloat.parse(str);
        if(temp.decimal_point()){
            if(temp.decimal_digits()==1){
                return str;
            }
            else {
                int index=str.length()-temp.decimal_digits();
                for(int i=str.length()-1;i>str.length()-temp.decimal_digits();i--){
                    if(str.charAt(i)!='0'){
                        index=i;
                        break;
                    }
                }
                String new_str="";
                for(int i=0;i<index+1;i++){
                    new_str+=str.charAt(i);
                }
                return new_str;
            }
        }
        return str;
    }

    public AFloat add(AFloat other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        int operand_1_decimal_length = this.decimal_digits();
        int operand_2_decimal_length = other.decimal_digits();
        if(!this.decimal_point()){
            operand_1+="."; //If the number doesn't have decimal point then add decimal point
        }
        if(!other.decimal_point()){
            operand_2+="."; //If the number doesn't have decimal point then add decimal point
        }
        String fin="";
        if(operand_1_decimal_length==operand_2_decimal_length){
            //Remove the decimal point and add them normally and then insert decimal point at proper position
            String str1="";
            String str2="";
            for(int i=0;i<operand_1.length();i++){
                if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i); //Converting the operands to integer by removing the point
            }
            for(int i=0;i<operand_2.length();i++){
                if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
            }
            AInteger temp1 = new AInteger(str1);//creating integer from floating point numbers by removing decimal points
            AInteger temp2 = new AInteger(str2);//creating integer from floating point numbers by removing decimal points
            AInteger res = temp1.add(temp2);//Adding the integer operands
            //res stands for result and fin stands for final
            if(operand_1_decimal_length==0)fin = res.get()+".0";//If the decimal length was 0 then directly adding .0 to the answer
            else{
                //Adding the decimal point by comparing the lengths of sum and decimal length
                if(res.get().length() > operand_1_decimal_length){
                    if(res.get().charAt(0)!='-'){ //The minus sign also will be added to the length which shouldn't be added.
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";//inserting at proper position
                        }
                    }
                    else {
                        if(res.get().length() > 1+operand_1_decimal_length){//case for minus sign being added to length of the sum
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";//insert at proper place
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+="0.";//insert at proper place
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){//if result is positive and decimal length is greater than length of sum
                        fin="0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length();i++){
                            fin+="0";
                        }
                        fin+=res.get();//Add the required number of zeroes and append the result to the fin variable
                    }
                    else{
                        fin="-0.";//Case for negative output
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
            AInteger temp1 = new AInteger(str1);//converting them to integer by removing decimal point
            AInteger temp2 = new AInteger(str2);//converting them to integer by removing decimal point
            AInteger res = temp1.add(temp2); //res: result fin: final
            if(operand_1_decimal_length==0)fin = res.get()+".0";//if decimal length is 0
            else{
                if(res.get().length() > operand_1_decimal_length){//if the result's length is greater than decimal length
                    if(res.get().charAt(0)!='-'){//if my output is positive.
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";//adding decimal point at proper place
                        }
                    }
                    else {
                        //if my output is negative
                        if(res.get().length() > 1+operand_1_decimal_length){
                            //Excluding the minus sign,if the result's length is greater than decimal length
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";//insert at proper position
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+="0.";//insert at proper place
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){//if output is positive
                        fin="0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length();i++){
                            fin+="0";//Add the required number of zeroes
                        }
                        fin+=res.get();//append the result to the fin variable
                    }
                    else{
                        fin="-0.";//if ouput is negative
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
            //pad some extra zeroes at the end of operand 1 to make decimal lengths equal
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
            AInteger temp1 = new AInteger(str1);//creating integer of operands by removing decimal point
            AInteger temp2 = new AInteger(str2);//creating integer of operands by removing decimal point
            AInteger res = temp1.add(temp2);//adding the integers
            //res: result fin: final
            if(operand_2_decimal_length==0)fin = res.get()+".0";
            else{
                if(res.get().length() > operand_2_decimal_length){//if result has more digits than decimal length
                    if(res.get().charAt(0)!='-'){//if output was positive
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_2_decimal_length)fin+=".";//insert point at proper position
                        }
                    }
                    else {//if output is negative
                        if(res.get().length() > 1+operand_2_decimal_length){
                            //if result, excluding minus sign has more digits than decimal length
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_2_decimal_length)fin+=".";//insert at proper position
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_2_decimal_length)fin+="0.";//insert at proper position
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){//if output is negative
                        fin="0.";
                        for(int i=0;i<operand_2_decimal_length-res.get().length();i++){
                            fin+="0";//adding required number of zeroes
                        }
                        fin+=res.get();//appending result to the variable fin
                    }
                    else{
                        fin="-0.";
                        for(int i=0;i<operand_2_decimal_length-res.get().length()+1;i++){
                            fin+="0";//adding required number of zeroes
                        }
                        for(int i=1;i<res.get().length();i++){
                            fin+=res.get().charAt(i);//appending result to the variable fin excluding minus sign
                        }
                    }
                }
            }
        }
        String fin_copy="";//making copy of fin to remove extra initial zeroes
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
        result.set(remove_extra_zeroes(fin_copy));//remove the extra zeroes at the end
        return result;
    }

    public AFloat sub(AFloat other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        int operand_1_decimal_length = this.decimal_digits();
        int operand_2_decimal_length = other.decimal_digits();
        if(!this.decimal_point()){
            operand_1+=".";//If the number doesn't have decimal point then add decimal point
        }
        if(!other.decimal_point()){
            operand_2+=".";//If the number doesn't have decimal point then add decimal point
        }
        String fin="";//fin: final
        if(operand_1_decimal_length==operand_2_decimal_length){
            String str1="";
            String str2="";
            for(int i=0;i<operand_1.length();i++){
                if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
            }
            for(int i=0;i<operand_2.length();i++){
                if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
            }
            //For subtraction remove the decimal point, and subtract them
            AInteger temp1 = new AInteger(str1);//converting operands to integers
            AInteger temp2 = new AInteger(str2);//converting operands to integers
            AInteger res = temp1.sub(temp2);//res : result
            if(operand_1_decimal_length==0)fin = res.get()+".0";//if decimal length is zero
            else{
                if(res.get().length() > operand_1_decimal_length){
                    if(res.get().charAt(0)!='-'){//if output is positive
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";//insert decimal point at proper position
                        }
                    }
                    else {//if output is negative
                        if(res.get().length() > 1+operand_1_decimal_length){
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";//insert decimal point at proper position
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+="0.";//insert decimal point at proper position
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){//if output was positive
                        fin="0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length();i++){
                            fin+="0";//adding required number of zeroes
                        }
                        fin+=res.get();//appending the res to fin
                    }
                    else{//output is negative
                        fin="-0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length()+1;i++){
                            fin+="0";//adding required number of zeroes
                        }
                        for(int i=1;i<res.get().length();i++){
                            fin+=res.get().charAt(i);//appending res to fin except the minus sign
                        }
                    }
                }
            }
        }
        //if decimal lengths are not equal
        else if(operand_1_decimal_length > operand_2_decimal_length){
            String str1="";
            String str2="";
            //padding zeroes to operand 2 to make decimal lengths equal
            for(int i=0;i<operand_1.length();i++){
                if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
            }
            for(int i=0;i<operand_2.length();i++){
                if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
            }
            for(int i=0;i<operand_1_decimal_length-operand_2_decimal_length;i++){
                str2+="0";
            }
            AInteger temp1 = new AInteger(str1);//converting float to integer to by removing the point
            AInteger temp2 = new AInteger(str2);//converting float to integer to by removing the point
            AInteger res = temp1.sub(temp2);//res : result
            if(operand_1_decimal_length==0)fin = res.get()+".0";//if decimal length is zero
            else{
                if(res.get().length() > operand_1_decimal_length){//if result has more digits than decimal length
                    if(res.get().charAt(0)!='-'){//if output is positive
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";//insert point at proper position
                        }
                    }
                    else {//if output is negative
                        if(res.get().length() > 1+operand_1_decimal_length){
                            //if result, excluding the minus sign has more digits than decimal length
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+=".";//insert point at proper position
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_1_decimal_length)fin+="0.";//insert point at proper position
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){//if output is positive
                        fin="0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length();i++){
                            fin+="0";//adding required number of zeroes
                        }
                        fin+=res.get();//insert at proper point
                    }
                    else{//if output is negative
                        fin="-0.";
                        for(int i=0;i<operand_1_decimal_length-res.get().length()+1;i++){
                            fin+="0";//adding required number of zeroes
                        }
                        for(int i=1;i<res.get().length();i++){
                            fin+=res.get().charAt(i);//insert at proper point except minus sign
                        }
                    }
                }
            }
        }
        else{
            String str1="";
            String str2="";
            //padding zeroes to operand 1 to make decimal lengths equal
            for(int i=0;i<operand_1.length();i++){
                if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
            }
            for(int i=0;i<operand_2.length();i++){
                if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
            }
            for(int i=0;i<operand_2_decimal_length-operand_1_decimal_length;i++){
                str1+="0";
            }
            AInteger temp1 = new AInteger(str1);//converting float to integers by removing decimal point
            AInteger temp2 = new AInteger(str2);//converting float to integers by removing decimal point
            AInteger res = temp1.sub(temp2);//res : result
            if(operand_2_decimal_length==0)fin = res.get()+".0";//if decimal length is zero
            else{
                if(res.get().length() > operand_2_decimal_length){
                    //if result has more digits than decimal length
                    if(res.get().charAt(0)!='-'){//if output is positive
                        for(int i=0;i<res.get().length();i++){
                            fin+=res.get().charAt(i);
                            if(i==res.get().length()-1-operand_2_decimal_length)fin+=".";//insert point at proper position
                        }
                    }
                    else {//if output is negative
                        if(res.get().length() > 1+operand_1_decimal_length){
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_2_decimal_length)fin+=".";//insert point at proper position
                            }
                        }
                        else {
                            for(int i=0;i<res.get().length();i++){
                                fin+=res.get().charAt(i);
                                if(i==res.get().length()-1-operand_2_decimal_length)fin+="0.";//insert point at proper position
                            }
                        }
                    }
                }
                else {
                    if(res.get().charAt(0)!='-'){
                        //output is positive
                        fin="0.";
                        for(int i=0;i<operand_2_decimal_length-res.get().length();i++){
                            fin+="0";//add required number of zeroes
                        }
                        fin+=res.get();//append the res to fin
                    }
                    else{
                        fin="-0.";//if output is negative
                        for(int i=0;i<operand_2_decimal_length-res.get().length()+1;i++){
                            fin+="0";//add required number of zeroes
                        }
                        for(int i=1;i<res.get().length();i++){
                            fin+=res.get().charAt(i);//append the res except the minus sign to fin
                        }
                    }
                }
            }
        }
        String fin_copy="";//copy of fin to remove extra zeroes at the start
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
        result.set(remove_extra_zeroes(fin_copy));//removing the last extra zeroes at the end
        return result;
    }

    public AFloat mul(AFloat other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        int operand_1_decimal_length = this.decimal_digits();
        int operand_2_decimal_length = other.decimal_digits();
        if(!this.decimal_point()){
            operand_1+=".";//If the number doesn't have decimal point then add decimal point
        }
        if(!other.decimal_point()){
            operand_2+=".";//If the number doesn't have decimal point then add decimal point
        }
        String str1 = "";
        String str2 = "";
        for(int i=0;i<operand_1.length();i++){
            if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);//converting float to integers
        }
        for(int i=0;i<operand_2.length();i++){
            if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);//converting float to integers
        }
        int start=0;
        String copy_str1="";//remove the extra zeroes at the start
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
        String copy_str2="";//remove the extra zeroes at the start
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
        AInteger res = temp1.mul(temp2);//res : result
        String fin="";//fin: final
        int total_decimal_length = operand_1_decimal_length+operand_2_decimal_length;//total decimal length
        if(res.get().charAt(0)!='-'){//if output is positive
            if(res.get().length()>total_decimal_length){
                //if res has more digits than total decimal length
                for(int i=0;i<res.get().length();i++){
                    fin+=res.get().charAt(i);
                    if(i==res.get().length()-1-total_decimal_length)fin+=".";//insert point at proper position
                }
                if(total_decimal_length==0)fin+="0";//if total decimal length is zero, then add 0 at the end
            }
            else {
                //if res has less digits than total decimal length
                fin="0.";
                for(int i=0;i<total_decimal_length-res.get().length();i++){
                    fin+="0";
                }
                fin+=res.get();//append res to the fin
            }
        }
        else {//if output is negative
            if(res.get().length()>1+total_decimal_length){
                //if res has more digits than total decimal length
                for(int i=0;i<res.get().length();i++){
                    fin+=res.get().charAt(i);
                    if(i==res.get().length()-1-total_decimal_length)fin+=".";
                }
                if(total_decimal_length==0)fin+="0";
            }
            else{
                //if res has less digits than total decimal length
                fin+="-0.";
                for(int i=0;i<total_decimal_length-res.get().length()+1;i++){
                    fin+="0";
                }
                for(int i=1;i<res.get().length();i++){
                    fin+=res.get().charAt(i);//append res to fin except the minus sign
                }
            }
        }
        String fin_copy="";//for removing extra zeroes at the start
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
        result.set(remove_extra_zeroes(fin_copy));//remove the extra zeroes at the end
        return result;
    }

    public AFloat div(AFloat other){
        String operand_1 = this.get();
        String operand_2 = other.get();
        if(!this.decimal_point()){
            operand_1+=".0";//If the number doesn't have decimal point then add decimal point
        }
        if(!other.decimal_point()){
            operand_2+=".0";//If the number doesn't have decimal point then add decimal point
        }
        AFloat operand_1_copy = AFloat.parse(operand_1);
        AFloat operand_2_copy = AFloat.parse(operand_2);
        int operand_1_decimal_length = operand_1_copy.decimal_digits();
        int operand_2_decimal_length = operand_2_copy.decimal_digits();
        String str1="";//converting the float to integer.
        String str2="";//converting the integer to float.
        for(int i=0;i<operand_1.length();i++){
            if(operand_1.charAt(i)!='.')str1+=operand_1.charAt(i);
        }
        for(int i=0;i<operand_2.length();i++){
            if(operand_2.charAt(i)!='.')str2+=operand_2.charAt(i);
        }
        int start=0;
        String copy_str1="";//removing extra zeroes at the start
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
        String copy_str2="";//remove extra zeroes at the start
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
            //division by zero is not allowed
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        if(copy_str1.equals("0")){
            //answer is zero
            AFloat result = new AFloat();//default constructor
            return result;
        }
        AInteger temp1 = new AInteger(copy_str1);
        AInteger temp2 = new AInteger(copy_str2);
        AInteger quot = temp1.div(temp2);//quot : quotient
        int pos=1;//checker if number is positive or negative
        if(copy_str1.charAt(0)=='-' || copy_str2.charAt(0)=='-'){
            if(copy_str1.charAt(0)=='-' && copy_str2.charAt(0)=='-')pos=1;
            else pos=0;
        }
        AInteger rem = temp1.sub(temp2.mul(quot));//rem: remainder
        if(rem.get().charAt(0)=='-'){
            String temp3="";
            for(int i=1;i<rem.get().length();i++)temp3+=rem.get().charAt(i);
            rem.set(temp3);
        }
        if(pos==0 && quot.get().charAt(0)!='-'&& !(rem.get().equals("0"))){
            quot.set("-"+quot.get());
        }
        int diff = operand_2_decimal_length - operand_1_decimal_length;//difference between the decimal lengths
        String deci="";
        String remainder = rem.get();
        int compare;//for number of digits to be added for getting 30 decimal length
        if(30+diff>0){
            compare=30+diff;
        }
        else {
            compare=0;
        }
        //implementing long division method
        for(int i=0;i<compare;i++){
            if(remainder.equals("0"))break;//if remainder becomes zero then break
            remainder+="0";
            AInteger temp4 = new AInteger(remainder);
            AInteger quotient = temp4.div(temp2);
            AInteger remain = temp4.sub(temp2.mul(quotient));
            if(rem.get().charAt(0)=='-'){
                String temp3="";//for removing the minus sign
                for(int j=1;i<remain.get().length();j++)temp3+=remain.get().charAt(j);
                remain.set(temp3);
            }
            remainder=remain.get();
            if(quotient.get().charAt(0)=='-'){
                String temp3="";//removing minus sign
                for(int j=1;j<quotient.get().length();j++)temp3+=quotient.get().charAt(j);
                quotient.set(temp3);
            }
            deci+=quotient.get();
        }
        String fin="";//fin: final
        if(diff==0){
        if(!deci.isEmpty())fin = quot.get() + "."+deci;
        else fin = quot.get() + ".0";
        }
        else if(diff>0){
            //you need to add zeroes and put decimal point at proper place
            fin+= quot.get();
            if(diff<deci.length()){
                for(int i=0;i<deci.length();i++){
                    fin+=deci.charAt(i);
                    if(i==diff-1)fin+=".";//insert point at proper position
                }
            }
            else if(diff==deci.length()){
                for(int i=0;i<deci.length();i++){
                    fin+=deci.charAt(i);
                }
                fin+=".0";//insert point at proper position
            }
            else {
                if(deci.isEmpty()){
                    for(int i=0;i<diff;i++){
                        fin+="0";
                    }
                    fin+=".0";//insert point at proper position
                }
                else {
                    for(int i=0;i<deci.length();i++){
                        fin+=deci.charAt(i);
                    }
                    for(int i=deci.length();i<diff;i++){
                        fin+="0";
                    }
                    fin+=".0";//insert point at proper position
                }
            }
        }
        else {
            int mod_diff=-1*diff;
            if(quot.get().charAt(0)!='-'){
                if(mod_diff<quot.get().length()){
                    for(int i=0;i<quot.get().length();i++){
                        fin+=quot.get().charAt(i);
                        if(i==quot.get().length()-1-mod_diff)fin+=".";//insert point at proper position
                    }
                    fin+=deci;
                }
                else if(mod_diff==quot.get().length()){
                    fin+="0."+quot.get()+deci;//insert point at proper position
                }
                else {
                    fin+="0.";//insert point at proper position
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
                        if(i==quot.get().length()-1-mod_diff)fin+=".";//insert point at proper position
                    }
                    fin+=deci;
                }
                else if(mod_diff==quot.get().length()-1){
                    fin+="-0.";//insert point at proper position
                    for(int i=1;i<quot.get().length();i++){
                        fin+=quot.get().charAt(i);
                    }
                    fin+=deci;
                }
                else {
                    fin+="-0.";//insert point at proper position
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
        String updated_fin="";//for removing the extra zeroes at the start
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
        AFloat checker = result.mul(other);
        if(checker.get().equals(operand_1_copy.get())){
            result.set(remove_extra_zeroes(updated_fin));//remove extra zeroes
        }
        return result;
    }
}