package com.dataGenerator;

public class Main {

    public static void main(String[] args){
        String[] myArray = {}
        com.dataGenerator.RecordGenerator recordGenerator = new com.dataGenerator.RecordGenerator();
        recordGenerator.generateRecords(args);

//        LdifReader reader = new LdifReader();
//        List<LdifEntry> entries = reader.parseLdifFile("/home/myname/export.ldif");
//        String dn = "cn=ravi ravi,mail=xyz@yahoo.com";
////iterate the entries
//        for (LdifEntry entry : entries) {
//            final String name = entry.getDn().getName();
//            if (name.equals(dn)) {
//                System.out.println(entry.get("cn"));
//                System.out.println(entry.get("mail"));
//                System.out.println(entry.get("mozillaNickname"));
//            }
//        }
    }
}
