package Programming;

public class LargeInteger {
    // DO NOT UPDATE ANY OF THE INSTANCE VARIABLES BELOW!
    int sign; // -1 for negative, 0 for zero, +1 for positive numbers
    int[] magnitude; // array representation of digits

    public LargeInteger(){}

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int[] getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int[] magnitude) {
        this.magnitude = magnitude;
    }

    public LargeInteger negate() {
        LargeInteger largeInteger = new LargeInteger();
        if(this.sign == -1){
            largeInteger.setSign(1);
            int[] negate_magnitude = new int[this.magnitude.length];
            for(int i = 0; i < negate_magnitude.length; i++) {
                negate_magnitude[i] = this.magnitude[i];
            }
            largeInteger.setMagnitude(negate_magnitude);
        }
        if(this.sign == 1){
            largeInteger.setSign(-1);
            int[] negate_magnitude = new int[this.magnitude.length];
            for(int i = 0; i < negate_magnitude.length; i++){
                negate_magnitude[i] = this.magnitude[i];
            }
            largeInteger.setMagnitude(negate_magnitude);
        }
        if(this.sign == 0){
            largeInteger.setSign(0);
            int[] negate_magnitude = new int[this.magnitude.length];
            for(int i = 0; i < negate_magnitude.length; i++) {
                negate_magnitude[i] = this.magnitude[i];
            }
            largeInteger.setMagnitude(negate_magnitude);
        }
        return largeInteger;
    }

    /**
     * Constructor for Large Integer
     *
     * @param numStr String representation of a large integer
     */
    public LargeInteger(String numStr) {
        // TODO fill in constructor
        String[] numStrArr = null;
        numStrArr = numStr.split("");
        if (!numStrArr[0].equals("0")) {
            if (!numStrArr[0].equals("-")) {
                sign = 1;
                magnitude = new int[numStrArr.length];
                for (int i = 0; i < numStrArr.length; i++) {
                    magnitude[i] = Integer.parseInt(numStrArr[i]);
                }
            }

            else {
                sign = -1;
                magnitude = new int[numStrArr.length - 1];
                for (int i = 0; i < numStrArr.length - 1; i++) {
                    magnitude[i] = Integer.parseInt(numStrArr[i + 1]);
                }
            }
        }

        else {
            sign = 0;
            magnitude = new int[] {0};
        }
    }

    public int MagnitudeToInteger(){
        String magnitude_to_string = "";
        for(int i : magnitude){
            magnitude_to_string = magnitude_to_string + i;
        }
        return Integer.parseInt(magnitude_to_string);
    }

    public static void main(String[] args) {
        LargeInteger test = new LargeInteger("-123456789");
        LargeInteger test_negate = test.negate();
        System.out.println("********** Test functions **********");
        System.out.println("test.sign = " + test.getSign());
        System.out.print("test.magnitude : ");
        for(int i : test.getMagnitude()){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("write test.magnitude as int : " + test.MagnitudeToInteger());
        System.out.println("********** Test deep copy **********");

        System.out.println("test_negate.sign = " + test_negate.getSign());
        System.out.print("test_negate.magnitude : ");
        for(int i : test_negate.getMagnitude()){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("write test_negate.magnitude as int : " + test_negate.MagnitudeToInteger());
    }
}
