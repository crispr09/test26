/*
 * package com.example.demo;
 * 
 * import java.rmi.RemoteException; import
 * java.security.NoSuchAlgorithmException; import java.util.Date; import
 * java.util.GregorianCalendar; import java.util.HashSet; import java.util.Set;
 * 
 * import org.identityconnectors.common.security.EncryptorFactory; import
 * org.identityconnectors.common.security.GuardedString; import
 * org.identityconnectors.common.security.SecurityUtil; import
 * org.identityconnectors.framework.common.objects.Attribute; import
 * org.identityconnectors.framework.common.objects.AttributeBuilder; import
 * org.identityconnectors.framework.common.objects.ConnectorObject; import
 * org.identityconnectors.framework.common.objects.Name; import
 * org.identityconnectors.framework.common.objects.OperationalAttributes; import
 * org.identityconnectors.framework.common.objects.ResultsHandler; import
 * org.identityconnectors.framework.impl.serializer.ObjectEncoder; import
 * org.junit.Assert; import org.testng.annotations.Test; public class
 * GuradedStringSecureHashExample { private static final String USER_NAME =
 * "hiiiii";
 * 
 * public static void main(String[] args) throws NoSuchAlgorithmException {
 * GuardedString guardedString = new GuardedString("Sridhar".toCharArray());
 * GuardedString cloneString = guardedString.copy();
 * guardedString.appendChar('J'); if(guardedString.equals(cloneString)){
 * System.out.println("Both are having same hash"); }else{
 * System.out.println("Both are having different hash"); }
 * System.out.println("Guarded String : " + guardedString.hashCode());
 * System.out.println("Clone String : " + cloneString.hashCode()); }
 * 
 * @Test public void testCreateFull() throws RemoteException { Set<Attribute>
 * attributes = new HashSet<Attribute>();
 * attributes.add(AttributeBuilder.build(Name.NAME, USER_NAME)); GuardedString
 * password = new GuardedString("Test1234".toCharArray());
 * attributes.add(AttributeBuilder.build(OperationalAttributes.PASSWORD_NAME,
 * password));
 * 
 * String title = "Mr."; String firstName = "James"; String lastName =
 * "Evolveum"; String titleAca1 = "Dr."; String email = "test@evolveum.com";
 * String telExt = "123"; String telNumber = "345"; String faxNumber = "567";
 * String faxExtens = "789"; String room = "12B"; String department = "IT";
 * String commType = "INT"; String country = "DE"; String languP = "D"; String
 * languIso = "DE"; attributes.add(AttributeBuilder.build("ADDRESS.TITLE_P",
 * title)); attributes.add(AttributeBuilder.build("ADDRESS.FIRSTNAME",
 * firstName)); attributes.add(AttributeBuilder.build("ADDRESS.LASTNAME",
 * lastName)); attributes.add(AttributeBuilder.build("ADDRESS.TITLE_ACA1",
 * titleAca1)); attributes.add(AttributeBuilder.build("ADDRESS.E_MAIL", email));
 * attributes.add(AttributeBuilder.build("ADDRESS.TEL1_EXT", telExt));
 * attributes.add(AttributeBuilder.build("ADDRESS.TEL1_NUMBR", telNumber));
 * attributes.add(AttributeBuilder.build("ADDRESS.FAX_NUMBER", faxNumber));
 * attributes.add(AttributeBuilder.build("ADDRESS.FAX_EXTENS", faxExtens));
 * attributes.add(AttributeBuilder.build("ADDRESS.ROOM_NO_P", room));
 * attributes.add(AttributeBuilder.build("ADDRESS.DEPARTMENT", department));
 * attributes.add(AttributeBuilder.build("ADDRESS.COMM_TYPE", commType));
 * attributes.add(AttributeBuilder.build("ADDRESS.COUNTRY", country));
 * attributes.add(AttributeBuilder.build("ADDRESS.LANGU_P", languP));
 * attributes.add(AttributeBuilder.build("ADDRESS.LANGU_ISO", languIso));
 * 
 * String spld = "LOCL"; attributes.add(AttributeBuilder.build("DEFAULTS.SPLD",
 * spld));
 * 
 * String licType = "55";
 * attributes.add(AttributeBuilder.build("UCLASS.LIC_TYPE", licType));
 * attributes.add(AttributeBuilder.build("UCLASS.SYSID", ""));//E:284:Parameter
 * SYSID must not be filled for this user type
 * attributes.add(AttributeBuilder.build("UCLASS.CLIENT", ""));//E:284:Parameter
 * CLIENT must not be filled for this user type
 * attributes.add(AttributeBuilder.build("UCLASS.BNAME_CHARGEABLE",
 * ""));//E:284:Parameter BNAME_CHARGEABLE must not be filled for this user type
 * 
 * boolean enable = false;
 * attributes.add(AttributeBuilder.build(OperationalAttributes.ENABLE_NAME,
 * enable));
 * 
 * String activityGroup = "ZBC_ADM_BENUTZERADMINISTRATOR";
 * 
 * Date enableDate = new GregorianCalendar(2016, 1, 1).getTime(); Date
 * disableDate = new GregorianCalendar(2016, 2, 31).getTime();
 * attributes.add(AttributeBuilder.build(OperationalAttributes.ENABLE_DATE_NAME,
 * enableDate.getTime()));// LOGONDATA.GLTGV
 * attributes.add(AttributeBuilder.build(OperationalAttributes.
 * DISABLE_DATE_NAME, disableDate.getTime()));//LOGONDATA.GLTGB final
 * ConnectorObject[] found = {null}; ResultsHandler handler = new
 * ResultsHandler() {
 * 
 * @Override public boolean handle(ConnectorObject connectorObject) { found[0] =
 * connectorObject; return true; // continue } };
 * 
 * 
 * // check attribute values ConnectorObject user = found[0]; //
 * Assert.assertTrue(user != null, "Created user " + USER_NAME + " not found");
 * 
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.TITLE_P").getValue().get
 * (0), title);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.FIRSTNAME").getValue().
 * get(0), firstName);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.LASTNAME").getValue().
 * get(0), lastName);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.TITLE_ACA1").getValue().
 * get(0), titleAca1);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.E_MAIL").getValue().get(
 * 0), email);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.TEL1_EXT").getValue().
 * get(0), telExt);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.TEL1_NUMBR").getValue().
 * get(0), telNumber);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.FAX_NUMBER").getValue().
 * get(0), faxNumber);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.FAX_EXTENS").getValue().
 * get(0), faxExtens);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.ROOM_NO_P").getValue().
 * get(0), room);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.DEPARTMENT").getValue().
 * get(0), department);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.COMM_TYPE").getValue().
 * get(0), commType);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.COUNTRY").getValue().get
 * (0), country);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.LANGU_P").getValue().get
 * (0), languP);
 * Assert.assertEquals(user.getAttributeByName("ADDRESS.LANGU_ISO").getValue().
 * get(0), languIso);
 * 
 * Assert.assertEquals(user.getAttributeByName("DEFAULTS.SPLD").getValue().get(0
 * ), spld);
 * 
 * Assert.assertEquals(user.getAttributeByName("UCLASS.LIC_TYPE").getValue().get
 * (0), licType); // empty not send //
 * Assert.assertEquals(user.getAttributeByName("UCLASS.SYSID").getValue().get(0)
 * , ""); //
 * Assert.assertEquals(user.getAttributeByName("UCLASS.CLIENT").getValue().get(0
 * ), ""); //
 * Assert.assertEquals(user.getAttributeByName("UCLASS.BNAME_CHARGEABLE").
 * getValue().get(0), "");
 * 
 * // special attributes
 * Assert.assertEquals(user.getAttributeByName(OperationalAttributes.ENABLE_NAME
 * ).getValue().get(0), enable);
 * 
 * 
 * Assert.assertEquals(user.getAttributeByName(OperationalAttributes.
 * ENABLE_DATE_NAME).getValue().get(0), enableDate.getTime());
 * Assert.assertEquals(user.getAttributeByName(OperationalAttributes.
 * DISABLE_DATE_NAME).getValue().get(0), disableDate.getTime()); }
 * 
 * 
 * 
 * }
 */