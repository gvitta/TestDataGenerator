package com.dataGenerator.utils;

public class RouterFunction {

    private HelperFunction helperFunction = new HelperFunction();
        public RouterFunction() {
        }

        public String getValue(String methodType, int width, int withoutpad, String pattern, String min, String max) {
            byte var8 = -1;
            switch(methodType.hashCode()) {
                case -2110278170:
                    if (methodType.equals("generateString")) {
                        var8 = 11;
                    }
                    break;
                case -2088565718:
                    if (methodType.equals("generateBetweenRange")) {
                        var8 = 13;
                    }
                    break;
                case -1972362074:
                    if (methodType.equals("generateCurrency")) {
                        var8 = 10;
                    }
                    break;
                case -1740542125:
                    if (methodType.equals("generateStringOfChar")) {
                        var8 = 9;
                    }
                    break;
                case 549772432:
                    if (methodType.equals("generateFixedPositiveAmount")) {
                        var8 = 5;
                    }
                    break;
                case 598787256:
                    if (methodType.equals("generateConstantValue")) {
                        var8 = 12;
                    }
                    break;
                case 643205907:
                    if (methodType.equals("generateSingleChar")) {
                        var8 = 8;
                    }
                    break;
                case 763020343:
                    if (methodType.equals("generateFixedAmount")) {
                        var8 = 4;
                    }
                    break;
                case 886708794:
                    if (methodType.equals("generateInt")) {
                        var8 = 0;
                    }
                    break;
                case 952357107:
                    if (methodType.equals("generateBoolean")) {
                        var8 = 7;
                    }
                    break;
                case 1355663472:
                    if (methodType.equals("generateFixedInt")) {
                        var8 = 2;
                    }
                    break;
                case 1662821933:
                    if (methodType.equals("generateAmount")) {
                        var8 = 3;
                    }
                    break;
                case 1718007491:
                    if (methodType.equals("generateDate")) {
                        var8 = 6;
                    }
                    break;
                case 1718259089:
                    if (methodType.equals("generateLong")) {
                        var8 = 1;
                    }
            }

            switch(var8) {
                case 0:
                    return this.helperFunction.generateInt();
                case 1:
                    return this.helperFunction.generateLong();
                case 2:
                    return this.helperFunction.generateFixedInt(width, withoutpad);
                case 3:
                    return this.helperFunction.generateAmount();
                case 4:
                    return this.helperFunction.generateFixedAmount(width, withoutpad);
                case 5:
                    return this.helperFunction.generateFixedPositiveAmount(width, withoutpad);
                case 6:
                    return this.helperFunction.generateDate(min, max, pattern);
                case 7:
                    return HelperFunction.generateBoolean(width);
                case 8:
                    return this.helperFunction.generateSingleChar();
                case 9:
                    return this.helperFunction.generateStringOfChar(width);
                case 10:
                    return HelperFunction.generateCurrency();
                case 11:
                    return HelperFunction.generateString(pattern);
                case 12:
                    return HelperFunction.generateConstantValue(pattern);
                case 13:
                    return this.helperFunction.generateBetweenRange(Long.parseLong(min), Long.parseLong(max));
                default:
                    return "no valid type";
            }
        }

        public String getAvroValue(String methodType, String min, String max, String pattern) {
            byte var6 = -1;
            switch(methodType.hashCode()) {
                case -2110278170:
                    if (methodType.equals("generateString")) {
                        var6 = 6;
                    }
                    break;
                case 3392903:
                    if (methodType.equals("null")) {
                        var6 = 8;
                    }
                    break;
                case 886708794:
                    if (methodType.equals("generateInt")) {
                        var6 = 0;
                    }
                    break;
                case 952357107:
                    if (methodType.equals("generateBoolean")) {
                        var6 = 3;
                    }
                    break;
                case 1662821933:
                    if (methodType.equals("generateAmount")) {
                        var6 = 1;
                    }
                    break;
                case 1717492726:
                    if (methodType.equals("generateBytes")) {
                        var6 = 5;
                    }
                    break;
                case 1718007491:
                    if (methodType.equals("generateDate")) {
                        var6 = 2;
                    }
                    break;
                case 1718049814:
                    if (methodType.equals("generateEnum")) {
                        var6 = 7;
                    }
                    break;
                case 1720794599:
                    if (methodType.equals("generateFloat")) {
                        var6 = 4;
                    }
            }

            switch(var6) {
                case 0:
                    return this.helperFunction.generateInt();
                case 1:
                    return this.helperFunction.generateAmount();
                case 2:
                    return this.helperFunction.generateDate(min, max, pattern);
                case 3:
                    return HelperFunction.generateBoolean(10);
                case 4:
                    return this.helperFunction.generateSingleChar();
                case 5:
                    return HelperFunction.generateCurrency();
                case 6:
                    return this.helperFunction.generateStringOfChar(10);
                case 7:
                    return HelperFunction.generateString(pattern);
                case 8:
                    return "No Valid value is mentioned in Config file ";
                default:
                    return "no valid type";
            }
        }
    }



