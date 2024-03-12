package XMLCreation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DRFBEPPacs003CD {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootelement = doc.createElement("Document");
        String ppp = "urn:iso:std:iso:20022:tech:xsd:pacs.004.001.02";
        String bbb = "http://www.w3.org/2001/XMLSchema-instance";
        rootelement.setAttribute("xmlns", ppp);
        rootelement.setAttribute("xmlns:xsi", bbb);
        doc.appendChild(rootelement);
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String TempMsgIdText = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(TempMsgIdText);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat aaa = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ccc = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat ddd = new SimpleDateFormat("HHmmss");
        String formattedDate = sdf.format(date);
        String OnlyDate = aaa.format(date);
        String currdate = formattedDate.replace(" ", "T");
        String Messagetime1 =ccc.format(date);
        String Messagetime2 =ddd.format(date);
        String Messagetime =Messagetime1+"T"+Messagetime2;
        System.out.println(currdate);
        System.out.println(OnlyDate);
        System.out.println(Messagetime);
        String MsgIdText ="EP15841"+Messagetime ;
        System.out.println(MsgIdText);
        
        //Adding elements to XML
        Element FIToFIPmtStsRpt = doc.createElement("FIToFIPmtStsRpt");
        rootelement.appendChild(FIToFIPmtStsRpt);

        Element GrpHdr = doc.createElement("GrpHdr");
        FIToFIPmtStsRpt.appendChild(GrpHdr);

        Element MsgId = doc.createElement("MsgId");
        GrpHdr.appendChild(MsgId);
        MsgId.setTextContent(MsgIdText);
        Element CreDtTm = doc.createElement("CreDtTm");
        GrpHdr.appendChild(CreDtTm);
        CreDtTm.setTextContent(currdate);

        
      //Elemet inside InstgAgt
        Element InstgAgt = doc.createElement("InstgAgt");
        Element FinInstnId_SuperTag1 = doc.createElement("FinInstnId");
        Element BICFI = doc.createElement("BIC");
        GrpHdr.appendChild(InstgAgt);
        InstgAgt.appendChild(FinInstnId_SuperTag1);
        FinInstnId_SuperTag1.appendChild(BICFI);
        BICFI.setTextContent("NNBANL2G");
        
      //Elemet inside InstdAgt
        Element InstdAgt1 = doc.createElement("InstdAgt");
        Element FinInstnId_SuperTag = doc.createElement("FinInstnId");
        Element BIC1 = doc.createElement("BIC");
        GrpHdr.appendChild(InstdAgt1);
        InstdAgt1.appendChild(FinInstnId_SuperTag);
        FinInstnId_SuperTag.appendChild(BIC1);
        BIC1.setTextContent("INNDNL2U");
        
        ////tag OrgnlGrpInfAndSts
       // PmtRtr.appendChild(TxInf);
        Element OrgnlGrpInfAndSts = doc.createElement("OrgnlGrpInfAndSts");
        FIToFIPmtStsRpt.appendChild(OrgnlGrpInfAndSts);
        Element OrgnlMsgId = doc.createElement("OrgnlMsgId");
        OrgnlGrpInfAndSts.appendChild(OrgnlMsgId);
        OrgnlMsgId.setTextContent("SB5321");
        
        //Create Element for OrgnlMsgNmId
        Element OrgnlMsgNmId = doc.createElement("OrgnlMsgNmId");
        OrgnlGrpInfAndSts.appendChild(OrgnlMsgNmId);
        OrgnlMsgNmId.setTextContent("pacs.003.001.02");
        
        //Create 
        //Create Element for OrgnlMsgNmId
        Element GrpSts = doc.createElement("GrpSts");
        OrgnlGrpInfAndSts.appendChild(GrpSts);
        GrpSts.setTextContent("PART");
        
        //Elements inside SttlmInf
        Element TxInfAndSts = doc.createElement("TxInfAndSts");
        FIToFIPmtStsRpt.appendChild(TxInfAndSts);
        Element StsId = doc.createElement("StsId");
        TxInfAndSts.appendChild(StsId);
        StsId.setTextContent("RtrId"+Messagetime1);
        
        Element OrgnlInstrId = doc.createElement("OrgnlInstrId");
        TxInfAndSts.appendChild(OrgnlInstrId);
        OrgnlInstrId.setTextContent("BEINSTRUCTION");
        
        Element OrgnlEndToEndId = doc.createElement("OrgnlEndToEndId");
        TxInfAndSts.appendChild(OrgnlEndToEndId);
        OrgnlEndToEndId.setTextContent("SEPACT/BA003");
        
        Element OrgnlTxId = doc.createElement("OrgnlTxId");
        TxInfAndSts.appendChild(OrgnlTxId);
        OrgnlTxId.setTextContent("BNKMLC0987");
        
        Element TxSts = doc.createElement("TxSts");
        TxInfAndSts.appendChild(TxSts);
        TxSts.setTextContent("RJCT");

        Element StsRsnInf = doc.createElement("StsRsnInf");
        TxInfAndSts.appendChild(StsRsnInf);
        Element Orgtr = doc.createElement("Orgtr");
        StsRsnInf.appendChild(Orgtr);
        Element Id = doc.createElement("Id");
        Orgtr.appendChild(Id);
        Element OrgId = doc.createElement("OrgId");
        Id.appendChild(OrgId);
        Element BICOrBEI = doc.createElement("BICOrBEI");
        OrgId.appendChild(BICOrBEI);
        BICOrBEI.setTextContent("NNBANL2G");
        
        
        Element Rsn = doc.createElement("Rsn");
        StsRsnInf.appendChild(Rsn);
        Element Cd = doc.createElement("Cd");
        Rsn.appendChild(Cd);
        Cd.setTextContent("RC01");
        
      // //tag OrgnlTxRef
      Element OrgnlTxRef = doc.createElement("OrgnlTxRef");
      TxInfAndSts.appendChild(OrgnlTxRef);
      Element IntrBkSttlmAmt = doc.createElement("IntrBkSttlmAmt");
      OrgnlTxRef.appendChild(IntrBkSttlmAmt);
      IntrBkSttlmAmt.setAttribute("Ccy","EUR");
      IntrBkSttlmAmt.setTextContent("17");
      Element IntrBkSttlmDt1 = doc.createElement("IntrBkSttlmDt");
      OrgnlTxRef.appendChild(IntrBkSttlmDt1);
      IntrBkSttlmDt1.setTextContent(OnlyDate);
      Element ReqdColltnDt = doc.createElement("ReqdColltnDt");
      OrgnlTxRef.appendChild(ReqdColltnDt);
      ReqdColltnDt.setTextContent(OnlyDate);
      
      Element CdtrSchmeId = doc.createElement("CdtrSchmeId");
      OrgnlTxRef.appendChild(CdtrSchmeId);
      Element Id1 = doc.createElement("Id");
      CdtrSchmeId.appendChild(Id1);
      Element PrvtId = doc.createElement("PrvtId");
      Id1.appendChild(PrvtId);
      Element Othr = doc.createElement("Othr");
      PrvtId.appendChild(Othr);
      Element Id1_1 = doc.createElement("Id");
      Othr.appendChild(Id1_1);
      Id1_1.setTextContent("NL16ZZZ320750020000");
      
      Element SchmeNm = doc.createElement("SchmeNm");
      Othr.appendChild(SchmeNm);
      Element Prtry1 = doc.createElement("Prtry");
      SchmeNm.appendChild(Prtry1);
      Prtry1.setTextContent("SEPA");
      
    
//    //Create Element for PmtTpInf
    
    
    Element PmtTpInf1 = doc.createElement("PmtTpInf");
    OrgnlTxRef.appendChild(PmtTpInf1);
    Element SvcLvl1 = doc.createElement("SvcLvl");
    PmtTpInf1.appendChild(SvcLvl1);
    Element Cd2 = doc.createElement("Cd");
    SvcLvl1.appendChild(Cd2);
    Cd2.setTextContent("SEPA");
    
    Element LclInstrm = doc.createElement("LclInstrm");
    PmtTpInf1.appendChild(LclInstrm);
    Element Cd1 = doc.createElement("Cd");
    LclInstrm.appendChild(Cd1);
    Cd1.setTextContent("CORE");
    
    Element SeqTp = doc.createElement("SeqTp");
    PmtTpInf1.appendChild(SeqTp);
    SeqTp.setTextContent("RCUR");
    
    Element CtgyPurp = doc.createElement("CtgyPurp");
    PmtTpInf1.appendChild(CtgyPurp);
    Element Cd11 = doc.createElement("Cd");
    CtgyPurp.appendChild(Cd11);
    Cd11.setTextContent("str1");
    
    //MndtRltdInf
    Element MndtRltdInf = doc.createElement("MndtRltdInf");
    OrgnlTxRef.appendChild(MndtRltdInf);
    Element MndtId = doc.createElement("MndtId");
    MndtRltdInf.appendChild(MndtId);
    MndtId.setTextContent("41017");
    
    Element DtOfSgntr = doc.createElement("DtOfSgntr");
    MndtRltdInf.appendChild(DtOfSgntr);
    DtOfSgntr.setTextContent(OnlyDate);
    
    Element AmdmntInd = doc.createElement("AmdmntInd");
    MndtRltdInf.appendChild(AmdmntInd);
    AmdmntInd.setTextContent("true");
    
    
    //AmdmntInfDtls
    Element AmdmntInfDtls = doc.createElement("AmdmntInfDtls");
    MndtRltdInf.appendChild(AmdmntInfDtls);
    
    Element OrgnlCdtrSchmeId = doc.createElement("OrgnlCdtrSchmeId");
    AmdmntInfDtls.appendChild(OrgnlCdtrSchmeId);
    
    Element Nm = doc.createElement("Nm");
    OrgnlCdtrSchmeId.appendChild(Nm);
    Nm.setTextContent("Vodafone Co");
    
    Element IdNew = doc.createElement("Id");
    OrgnlCdtrSchmeId.appendChild(IdNew);
    
    
    Element PrvtId1 = doc.createElement("PrvtId");
    IdNew.appendChild(PrvtId1);
    
    
    Element Othr1 = doc.createElement("Othr");
    PrvtId1.appendChild(Othr1);
    
    Element ID = doc.createElement("Id");
    Othr1.appendChild(ID);
    ID.setTextContent("NL16ZZZ320750020000");
    
    Element SchmeNm1 = doc.createElement("SchmeNm");
    Othr1.appendChild(SchmeNm1);
    Element Prtry11 = doc.createElement("Prtry");
    SchmeNm1.appendChild(Prtry11);
    Prtry11.setTextContent("SEPA");
    
    
   
    
    
    
    Element OrgnlDbtrAcct = doc.createElement("OrgnlDbtrAcct");
    AmdmntInfDtls.appendChild(OrgnlDbtrAcct);
    
    Element Idd = doc.createElement("Id");
    OrgnlDbtrAcct.appendChild(Idd);
    Element IBAN = doc.createElement("IBAN");
    Idd.appendChild(IBAN);
    IBAN.setTextContent("NL14NNBA2040109771");
    
    //Tag ElctrncSgntr2
    Element ElctrncSgntr2 = doc.createElement("ElctrncSgntr");
    MndtRltdInf.appendChild(ElctrncSgntr2);
    ElctrncSgntr2.setTextContent("DIANA");
    
    //MndtRltdInf
    Element RmtInf = doc.createElement("RmtInf");
    OrgnlTxRef.appendChild(RmtInf);
    Element Ustrd = doc.createElement("Ustrd");
    RmtInf.appendChild(Ustrd);
    Ustrd.setTextContent("Direct Debit Collection");
    
    //UltmtDBtr
    Element UltmtDbtr = doc.createElement("UltmtDbtr");
    OrgnlTxRef.appendChild(UltmtDbtr);
    Element Nm_new = doc.createElement("Nm");
    UltmtDbtr.appendChild(Nm_new);
    Nm_new.setTextContent("str1234");
    
    Element Ida = doc.createElement("Id");
    UltmtDbtr.appendChild(Ida);
    Element OrgId2 = doc.createElement("OrgId");
    Ida.appendChild(OrgId2);
    Element BICOrBEI1 = doc.createElement("BICOrBEI");
    OrgId2.appendChild(BICOrBEI1);
    BICOrBEI1.setTextContent("NNBANL2G");
    
  //UltmtDBtr
    Element Dbtr = doc.createElement("Dbtr");
    OrgnlTxRef.appendChild(Dbtr);
    Element Nm_new1 = doc.createElement("Nm");
    Dbtr.appendChild(Nm_new1);
    Nm_new1.setTextContent("D. Demir Halk Debtor");
    Element PstlAdr = doc.createElement("PstlAdr");
    Dbtr.appendChild(PstlAdr);
    
//  //DbtrAcct
  Element DbtrAcct = doc.createElement("DbtrAcct");
  OrgnlTxRef.appendChild(DbtrAcct);
  Element Id11 = doc.createElement("Id");
  DbtrAcct.appendChild(Id11);
  Element IBAN1 = doc.createElement("IBAN");
  Id11.appendChild(IBAN1);
  IBAN1.setTextContent("NL14NNBA2040109771");
  
////DbtrAgt
Element DbtrAgt = doc.createElement("DbtrAgt");
OrgnlTxRef.appendChild(DbtrAgt);
Element FinInstnId1 = doc.createElement("FinInstnId");
DbtrAgt.appendChild(FinInstnId1);
Element BIC11 = doc.createElement("BIC");
FinInstnId1.appendChild(BIC11);
BIC11.setTextContent("NNBANL2G");

////CdtrAgt
Element CdtrAgt = doc.createElement("CdtrAgt");
OrgnlTxRef.appendChild(CdtrAgt);
Element FinInstnId2 = doc.createElement("FinInstnId");
CdtrAgt.appendChild(FinInstnId2);
Element BIC2 = doc.createElement("BIC");
FinInstnId2.appendChild(BIC2);
BIC2.setTextContent("ABNANL2A");
    

Element Cdtr = doc.createElement("Cdtr");
OrgnlTxRef.appendChild(Cdtr);
Element Nm_new11 = doc.createElement("Nm");
Cdtr.appendChild(Nm_new11);
Nm_new11.setTextContent("Vodafon Co");

//Tag PstlAdr
Element PstlAdr1 = doc.createElement("PstlAdr");
Cdtr.appendChild(PstlAdr1);
Element Ctry1 = doc.createElement("Ctry");
PstlAdr1.appendChild(Ctry1);
Ctry1.setTextContent("NL");
//<AdrLine>s Gravenhof 2 D</AdrLine>
//<AdrLine>7301 DN ZUTPHEN</AdrLine>
Element AdrLine1 = doc.createElement("AdrLine");
PstlAdr1.appendChild(AdrLine1);
AdrLine1.setTextContent("s Gravenhof 2 D");
Element AdrLine1_1 = doc.createElement("AdrLine");
PstlAdr1.appendChild(AdrLine1_1);
AdrLine1_1.setTextContent("s Gravenhof 2 D");

//// Element inside  CdtrAcct
Element CdtrAcct1 = doc.createElement("CdtrAcct");
OrgnlTxRef.appendChild(CdtrAcct1);
Element ID6 = doc.createElement("Id");
CdtrAcct1.appendChild(ID6);
Element IBAN11 = doc.createElement("IBAN");
ID6.appendChild(IBAN11);
IBAN11.setTextContent("NL91ABNA04171643100");

//Tag UltmtCdtr
Element UltmtCdtr1 = doc.createElement("UltmtCdtr");
OrgnlTxRef.appendChild(UltmtCdtr1);
Element Nm1_1 = doc.createElement("Nm");
UltmtCdtr1.appendChild(Nm1_1);
Nm1_1.setTextContent("Vodafon Co");

Element Id1_11 = doc.createElement("Id");
UltmtCdtr1.appendChild(Id1_11);

Element OrgId11 = doc.createElement("OrgId");
Id1_11.appendChild(OrgId11);

Element BICOrBEI11 = doc.createElement("BICOrBEI");
OrgId11.appendChild(BICOrBEI11);
BICOrBEI11.setTextContent("ABNANL2A");





    
        
//        
//        //tag OrgnlGrpInf
//        PmtRtr.appendChild(TxInf);
//        Element OrgnlGrpInf = doc.createElement("OrgnlGrpInf");
//        TxInf.appendChild(OrgnlGrpInf);
//        Element OrgnlMsgId = doc.createElement("OrgnlMsgId");
//        OrgnlGrpInf.appendChild(OrgnlMsgId);
//        OrgnlMsgId.setTextContent("PI231160XX6B11ZV");
//        
//        //Create Element for OrgnlMsgNmId
//        Element OrgnlMsgNmId = doc.createElement("OrgnlMsgNmId");
//        OrgnlGrpInf.appendChild(OrgnlMsgNmId);
//        OrgnlMsgNmId.setTextContent("pacs.008.001.02");
//        
//      //tag OrgnlGrpInf
//        PmtRtr.appendChild(TxInf);
//        Element OrgnlInstrId = doc.createElement("OrgnlInstrId");
//        TxInf.appendChild(OrgnlInstrId);
//        OrgnlInstrId.setTextContent("NOTPROVIDED");
//        
//      //tag OrgnlEndToEndId  
//        PmtRtr.appendChild(TxInf);
//        Element OrgnlEndToEndId = doc.createElement("OrgnlEndToEndId");
//        TxInf.appendChild(OrgnlEndToEndId);
//        OrgnlEndToEndId.setTextContent("BNK23116DJFLJFGK");
//        
//        //Tag OrgnlTxId
//        PmtRtr.appendChild(TxInf);
//        Element OrgnlTxId = doc.createElement("OrgnlTxId");
//        TxInf.appendChild(OrgnlTxId);
//        OrgnlTxId.setTextContent("BNK23116DJFLJFGK");
//        
//        //Tag OrgnlIntrBkSttlmAmt
//        Element OrgnlIntrBkSttlmAmt = doc.createElement("OrgnlIntrBkSttlmAmt");
//        TxInf.appendChild(OrgnlIntrBkSttlmAmt);
//        OrgnlIntrBkSttlmAmt.setAttribute("Ccy","EUR");
//        OrgnlIntrBkSttlmAmt.setTextContent("103.00");
//        
//        //Tag RtrdIntrBkSttlmAmt
//        Element RtrdIntrBkSttlmAmt = doc.createElement("RtrdIntrBkSttlmAmt");
//        TxInf.appendChild(RtrdIntrBkSttlmAmt);
//        RtrdIntrBkSttlmAmt.setAttribute("Ccy","EUR");
//        RtrdIntrBkSttlmAmt.setTextContent("103.00");
//        
//        //tag ChrgsInf
//        PmtRtr.appendChild(TxInf);
//        Element ChrgsInf = doc.createElement("ChrgsInf");
//        TxInf.appendChild(ChrgsInf);
//        Element Amt = doc.createElement("Amt");
//        ChrgsInf.appendChild(Amt);
//        Amt.setAttribute("Ccy","EUR");
//        Amt.setTextContent("0");
//        
//        //Tag Pty
//        Element Pty = doc.createElement("Pty");
//        ChrgsInf.appendChild(Pty);
//        Element FinInstnId = doc.createElement("FinInstnId");
//        Pty.appendChild(FinInstnId);
//        Element BIC = doc.createElement("BIC");
//        FinInstnId.appendChild(BIC);
//        BIC.setTextContent("NNBANL2G");
//        
//        // //tag RtrRsnInf
//        Element RtrRsnInf = doc.createElement("RtrRsnInf");
//        TxInf.appendChild(RtrRsnInf);
//        Element Orgtr = doc.createElement("Orgtr");
//        RtrRsnInf.appendChild(Orgtr);
//        Element Id = doc.createElement("Id");
//        Orgtr.appendChild(Id);
//        Element OrgId = doc.createElement("OrgId");
//        Id.appendChild(OrgId);
//        Element BICOrBEI = doc.createElement("BICOrBEI");
//        OrgId.appendChild(BICOrBEI);
//        BICOrBEI.setTextContent("NNBANL2G");
//        
//        //Tag
//        Element Rsn = doc.createElement("Rsn");
//        RtrRsnInf.appendChild(Rsn);
//        Element Cd1 = doc.createElement("Cd");
//        Rsn.appendChild(Cd1);
//        Cd1.setTextContent("AC01");
//        

//        
//        //Elements inside SttlmInf
//        Element SttlmInf1 = doc.createElement("SttlmInf");
//        OrgnlTxRef.appendChild(SttlmInf1);
//        Element SttlmMtd1 = doc.createElement("SttlmMtd");
//        SttlmInf1.appendChild(SttlmMtd1);
//        SttlmMtd1.setTextContent("CLRG");
//        

//        //
////Create Element for PmtTpInf
//        
//        
//        Element RmtInf = doc.createElement("RmtInf");
//        OrgnlTxRef.appendChild(RmtInf);
//        Element Ustrd = doc.createElement("Ustrd");
//        RmtInf.appendChild(Ustrd);
//        Ustrd.setTextContent("Rmsg from EP+ SCT");
//        
//        //Dbtr
//        
//        Element Dbtr = doc.createElement("Dbtr");
//        OrgnlTxRef.appendChild(Dbtr);
//        Element Nm = doc.createElement("Nm");
//        Dbtr.appendChild(Nm);
//        Nm.setTextContent("Sabari");
//
//    
//        
//      
//        Date date1 = new Date();
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat aaa1 = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat ccc1 = new SimpleDateFormat("yyyyMMdd");
//        SimpleDateFormat ddd1 = new SimpleDateFormat("HHmmss");
//        String formattedDate1 = sdf1.format(date);
//        String OnlyDate1 = aaa1.format(date);
//        String currdate1 = formattedDate.replace(" ", "T");
//        String Messagetime11 =ccc1.format(date);
//        String Messagetime21 =ddd1.format(date);
//        String Messagetime111 =Messagetime11+"T"+Messagetime21;
//        String MsgIdText1 ="000000000000"+Messagetime111 ;
//        System.out.println(MsgIdText1);
//       // InstrId.setTextContent(MsgIdText1);
//        Element EndToEndId = doc.createElement("EndToEndId");
//
//        String EndToEndIdText = "E2E"+Messagetime111;
//        EndToEndId.setTextContent(EndToEndIdText);
//        Element TxId = doc.createElement("TxId");
//        TxId.setTextContent("TxId"+Messagetime111);
//        

//        

//        

//        
//      //Element inside Cdtr
//        Element Cdtr1 = doc.createElement("Cdtr");
//        OrgnlTxRef.appendChild(Cdtr1);
//        Element Nm2 = doc.createElement("Nm");
//        Cdtr1.appendChild(Nm2);
//        Nm2.setTextContent("Corry");
//        

//        
//        
//        
//       
//       


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("DRFBEPPACS003CD.xml"));
        transformer.transform(source, result);


    }



}



