public class MathFunc {
    public static double func(int var, String[] args){

        int t = 0;
        double result = 0;
        switch (var){
            case 1 -> {
                double x = Double.parseDouble(args[t++]);
                double a = Double.parseDouble(args[t++]);
                double n = Double.parseDouble(args[t++]);
                double b = Double.parseDouble(args[t++]);
                double c = Double.parseDouble(args[t]);
                result = 5*Math.pow(a, x*n) / (b + c) - Math.sqrt(Math.abs(Math.cos(x*x*x)));
            }
            case 2 -> {
                double x = Double.parseDouble(args[t++]);
                double a = Double.parseDouble(args[t++]);
                double y = Double.parseDouble(args[t++]);
                double e = Double.parseDouble(args[t++]);
                double w = Double.parseDouble(args[t]);
                result = Math.abs(x - y) / Math.pow(1 + 2*x, a) - Math.pow(e, 1 + w);
            }
            case 3 -> {
                double x = Double.parseDouble(args[t++]);
                double a0 = Double.parseDouble(args[t++]);
                double a1 = Double.parseDouble(args[t++]);
                double a2 = Double.parseDouble(args[t]);
                result = Math.sqrt(a0 + a1*x + a2*Math.cbrt(Math.abs(Math.sin(x))));
            }
            case 4 -> {
                double x = Double.parseDouble(args[t++]);
                double a = Double.parseDouble(args[t]);
                result = Math.log(Math.abs(Math.pow(a, 7))) + Math.atan(x*x) + Math.PI / Math.sqrt(Math.abs(a + x));
            }
            case 5 -> {
                double a = Double.parseDouble(args[t++]);
                double b = Double.parseDouble(args[t++]);
                double c = Double.parseDouble(args[t++]);
                double d = Double.parseDouble(args[t++]);
                double e = Double.parseDouble(args[t]);
                result = Math.pow(Math.pow(a + b, 2) / (c + d) + Math.pow(e, Math.sqrt(Math.PI + 1)), 0.2);
            }
            case 6 -> {
                double x = Double.parseDouble(args[t++]);
                double e = Double.parseDouble(args[t]);
                result = e * (2 * Math.sin(4*x) + Math.pow(Math.cos(x*x), 2)) / 3*x;
            }
            case 7 -> {
                double x = Double.parseDouble(args[t]);
                result = 0.25 * ((1 + x*x)/(1 - x) + 0.5*Math.tan(x));}}
        return result;
    }


}
