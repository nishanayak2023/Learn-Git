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

public class DRFBEPCurrentDtPACS003 {
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
        String MsgIdText ="INNDNL2U"+Messagetime ;
        System.out.println(MsgIdText);
        
        //Adding elements to XML
        Element PmtRtr = doc.createElement("PmtRtr");
        rootelement.appendChild(PmtRtr);

        Element GrpHdr = doc.createElement("GrpHdr");
        PmtRtr.appendChild(GrpHdr);

        Element MsgId = doc.createElement("MsgId");
        GrpHdr.appendChild(MsgId);
        MsgId.setTextContent(MsgIdText);
        Element CreDtTm = doc.createElement("CreDtTm");
        GrpHdr.appendChild(CreDtTm);
        CreDtTm.setTextContent(currdate);
        Element NbOfTxs = doc.createElement("NbOfTxs");
        GrpHdr.appendChild(NbOfTxs);
        NbOfTxs.setTextContent("1");
        Element TtlRtrdIntrBkSttlmAmt = doc.createElement("TtlRtrdIntrBkSttlmAmt");
        GrpHdr.appendChild(TtlRtrdIntrBkSttlmAmt);
        TtlRtrdIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        TtlRtrdIntrBkSttlmAmt.setTextContent("14.00");
        Element IntrBkSttlmDt = doc.createElement("IntrBkSttlmDt");
        GrpHdr.appendChild(IntrBkSttlmDt);
        IntrBkSttlmDt.setTextContent(OnlyDate);
        
        //Elements inside SttlmInf
        Element SttlmInf = doc.createElement("SttlmInf");
        GrpHdr.appendChild(SttlmInf);
        Element SttlmMtd = doc.createElement("SttlmMtd");
        SttlmInf.appendChild(SttlmMtd);
        SttlmMtd.setTextContent("CLRG");
        Element ClrSys = doc.createElement("ClrSys");
        SttlmInf.appendChild(ClrSys);
        Element Cd1_New = doc.createElement("Cd");
        ClrSys.appendChild(Cd1_New);
        Cd1_New.setTextContent("INC");
//        
//        
//        
//        
//        
//        

        
        
        

        //Elemet inside InstgAgt
        Element InstgAgt = doc.createElement("InstgAgt");
        Element FinInstnId_SuperTag1 = doc.createElement("FinInstnId");
        Element BICFI = doc.createElement("BIC");
        GrpHdr.appendChild(InstgAgt);
        InstgAgt.appendChild(FinInstnId_SuperTag1);
        FinInstnId_SuperTag1.appendChild(BICFI);
        BICFI.setTextContent("NNBANL2G");
        
        

        Element TxInf = doc.createElement("TxInf");
        Element RtrId = doc.createElement("RtrId");
        TxInf.appendChild(RtrId);
        RtrId.setTextContent("RtrId"+OnlyDate+"INNDNL61488");
        
        //tag OrgnlGrpInf
        PmtRtr.appendChild(TxInf);
        Element OrgnlGrpInf = doc.createElement("OrgnlGrpInf");
        TxInf.appendChild(OrgnlGrpInf);
        Element OrgnlMsgId = doc.createElement("OrgnlMsgId");
        OrgnlGrpInf.appendChild(OrgnlMsgId);
        OrgnlMsgId.setTextContent("TPS0000000000145709132640");
        
        //Create Element for OrgnlMsgNmId
        Element OrgnlMsgNmId = doc.createElement("OrgnlMsgNmId");
        OrgnlGrpInf.appendChild(OrgnlMsgNmId);
        OrgnlMsgNmId.setTextContent("pacs.003.001.02");
        
      //tag OrgnlGrpInf
        PmtRtr.appendChild(TxInf);
        Element OrgnlInstrId = doc.createElement("OrgnlInstrId");
        TxInf.appendChild(OrgnlInstrId);
        OrgnlInstrId.setTextContent("INNDNL2U"+OnlyDate);
        
      //tag OrgnlEndToEndId  
        PmtRtr.appendChild(TxInf);
        Element OrgnlEndToEndId = doc.createElement("OrgnlEndToEndId");
        TxInf.appendChild(OrgnlEndToEndId);
        OrgnlEndToEndId.setTextContent("100082644");
        
        //Tag OrgnlTxId
        PmtRtr.appendChild(TxInf);
        Element OrgnlTxId = doc.createElement("OrgnlTxId");
        TxInf.appendChild(OrgnlTxId);
        OrgnlTxId.setTextContent("BNK230930JD0KFFK");
        
        //Tag OrgnlIntrBkSttlmAmt
        Element OrgnlIntrBkSttlmAmt = doc.createElement("OrgnlIntrBkSttlmAmt");
        TxInf.appendChild(OrgnlIntrBkSttlmAmt);
        OrgnlIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        OrgnlIntrBkSttlmAmt.setTextContent("14.00");
        
        //Tag RtrdIntrBkSttlmAmt
        Element RtrdIntrBkSttlmAmt = doc.createElement("RtrdIntrBkSttlmAmt");
        TxInf.appendChild(RtrdIntrBkSttlmAmt);
        RtrdIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        RtrdIntrBkSttlmAmt.setTextContent("14.00");
        
        
        // //tag RtrRsnInf
        Element RtrRsnInf = doc.createElement("RtrRsnInf");
        TxInf.appendChild(RtrRsnInf);
        Element Orgtr = doc.createElement("Orgtr");
        RtrRsnInf.appendChild(Orgtr);
        Element Id = doc.createElement("Id");
        Orgtr.appendChild(Id);
        Element OrgId = doc.createElement("OrgId");
        Id.appendChild(OrgId);
        Element BICOrBEI = doc.createElement("BICOrBEI");
        OrgId.appendChild(BICOrBEI);
        BICOrBEI.setTextContent("NNBANL2G");
        
        //Tag
        Element Rsn = doc.createElement("Rsn");
        RtrRsnInf.appendChild(Rsn);
        Element Cd111 = doc.createElement("Cd");
        Rsn.appendChild(Cd111);
        Cd111.setTextContent("AC06");
        
        // //tag OrgnlTxRef
        Element OrgnlTxRef = doc.createElement("OrgnlTxRef");
        TxInf.appendChild(OrgnlTxRef);
        Element IntrBkSttlmDt1 = doc.createElement("IntrBkSttlmDt");
        OrgnlTxRef.appendChild(IntrBkSttlmDt1);
        IntrBkSttlmDt1.setTextContent(OnlyDate);
        
//      //ReqdColltnDt
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
        
      //Create Element for OrgnlMsgNmId
        Element PmtTpInf = doc.createElement("PmtTpInf");
        OrgnlTxRef.appendChild(PmtTpInf);
        Element SvcLvl = doc.createElement("SvcLvl");
        PmtTpInf.appendChild(SvcLvl);
        Element Cd = doc.createElement("Cd");
        SvcLvl.appendChild(Cd);
        Cd.setTextContent("SEPA");
        
        //Create Element for PmtTpInf
        Element LclInstrm = doc.createElement("LclInstrm");
        PmtTpInf.appendChild(LclInstrm);
        Element Cd1 = doc.createElement("Cd");
        LclInstrm.appendChild(Cd1);
        Cd1.setTextContent("CORE");
        
        Element SeqTp = doc.createElement("SeqTp");
        PmtTpInf.appendChild(SeqTp);
        SeqTp.setTextContent("RCUR");
        
        Element CtgyPurp = doc.createElement("CtgyPurp");
        PmtTpInf.appendChild(CtgyPurp);
        Element cd = doc.createElement("Cd");
        CtgyPurp.appendChild(cd);
        cd.setTextContent("str1");
        
        
        Element MndtRltdInf = doc.createElement("MndtRltdInf");
        OrgnlTxRef.appendChild(MndtRltdInf);
        Element MndtId = doc.createElement("MndtId");
        MndtRltdInf.appendChild(MndtId);
        MndtId.setTextContent("41014");

        Element DtOfSgntr = doc.createElement("DtOfSgntr");
        MndtRltdInf.appendChild(DtOfSgntr);
        DtOfSgntr.setTextContent(OnlyDate);
        Element AmdmntInd = doc.createElement("AmdmntInd");
        MndtRltdInf.appendChild(AmdmntInd);
        AmdmntInd.setTextContent("true");
        
        //AmdmntInfDtls
        Element AmdmntInfDtls2 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf.appendChild(AmdmntInfDtls2);
        Element OrgnlCdtrSchmeId2 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls2.appendChild(OrgnlCdtrSchmeId2);
        Element Nm2 = doc.createElement("Nm");
        OrgnlCdtrSchmeId2.appendChild(Nm2);
        Nm2.setTextContent("Company 0");
        
        Element Id2 = doc.createElement("Id");
        OrgnlCdtrSchmeId2.appendChild(Id2);
        //PrvtId
        
        Element PrvtId2 = doc.createElement("PrvtId");
        Id2.appendChild(PrvtId2);
        //Othr
        Element Othr2 = doc.createElement("Othr");
        PrvtId2.appendChild(Othr2);
        Element Id2_2 = doc.createElement("Id");
        Othr2.appendChild(Id2_2);
        Id2_2.setTextContent("NL16ZZZ320750020000");
        
        
        Element SchmeNm1 = doc.createElement("SchmeNm");
        Othr2.appendChild(SchmeNm1);
        Element Prtry11 = doc.createElement("Prtry");
        SchmeNm1.appendChild(Prtry11);
        Prtry11.setTextContent("SEPA");
        
        Element OrgnlDbtrAcct = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls2.appendChild(OrgnlDbtrAcct);
        
        Element Idd = doc.createElement("Id");
        OrgnlDbtrAcct.appendChild(Idd);
        Element IBAN = doc.createElement("IBAN");
        Idd.appendChild(IBAN);
        IBAN.setTextContent("NL61NNBA0719053412");
        
        
        //ElctrncSgntr
        Element ElctrncSgntr = doc.createElement("ElctrncSgntr");
        MndtRltdInf.appendChild(ElctrncSgntr);
        ElctrncSgntr.setTextContent("G-FLOW");
        
        
      //RmtInf
        Element RmtInf1 = doc.createElement("RmtInf");
        OrgnlTxRef.appendChild(RmtInf1);
        Element Ustrd1 = doc.createElement("Ustrd");
        RmtInf1.appendChild(Ustrd1);
        Ustrd1.setTextContent("Collection 0");
        
        
        Element UltmtDbtr2 = doc.createElement("UltmtDbtr");
        OrgnlTxRef.appendChild(UltmtDbtr2);
        Element Nm2_2_2 = doc.createElement("Nm");
        UltmtDbtr2.appendChild(Nm2_2_2);
        Nm2_2_2.setTextContent("Ult debtor 0");
        
        Element Id111 = doc.createElement("Id");
        UltmtDbtr2.appendChild(Id111);
        Element OrgId111 = doc.createElement("OrgId");
        Id111.appendChild(OrgId111);
        Element BICOrBEI111 = doc.createElement("BICOrBEI");
        OrgId111.appendChild(BICOrBEI111);
        BICOrBEI111.setTextContent("NNBANL2G");
        
      //Dbtr

        Element Dbtr = doc.createElement("Dbtr");
        OrgnlTxRef.appendChild(Dbtr);
        Element Nm11 = doc.createElement("Nm");
        Dbtr.appendChild(Nm11);
        Nm11.setTextContent("Debtor 0");
        
 //Tag PstlAdr
        
        Element PstlAdr2_2_2 = doc.createElement("PstlAdr");
        Dbtr.appendChild(PstlAdr2_2_2);
        Element AdrLine = doc.createElement("AdrLine");
        PstlAdr2_2_2.appendChild(AdrLine);
        AdrLine.setTextContent("Milkyway 0");
 
        
       

        
        
        
      
        
        


    
        
      
        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat aaa1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ccc1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat ddd1 = new SimpleDateFormat("HHmmss");
        String formattedDate1 = sdf1.format(date);
        String OnlyDate1 = aaa1.format(date);
        String currdate1 = formattedDate.replace(" ", "T");
        String Messagetime11 =ccc1.format(date);
        String Messagetime21 =ddd1.format(date);
        String Messagetime111 =Messagetime11+"T"+Messagetime21;
        String MsgIdText1 ="000000000000"+Messagetime111 ;
        System.out.println(MsgIdText1);
       // InstrId.setTextContent(MsgIdText1);
        Element EndToEndId = doc.createElement("EndToEndId");

//        String EndToEndIdText = "E2E"+Messagetime111;
//        EndToEndId.setTextContent(EndToEndIdText);
//        Element TxId = doc.createElement("TxId");
//        TxId.setTextContent("TxId"+Messagetime111);
        
        //DbtrAcct
        Element DbtrAcct = doc.createElement("DbtrAcct");
        OrgnlTxRef.appendChild(DbtrAcct);
        Element Id11 = doc.createElement("Id");
        DbtrAcct.appendChild(Id11);
        Element IBAN1 = doc.createElement("IBAN");
        Id11.appendChild(IBAN1);
        IBAN1.setTextContent("NL61NNBA0719053412");
        
        //DbtrAgt
        Element DbtrAgt = doc.createElement("DbtrAgt");
        OrgnlTxRef.appendChild(DbtrAgt);
        Element FinInstnId1 = doc.createElement("FinInstnId");
        DbtrAgt.appendChild(FinInstnId1);
        Element BIC1 = doc.createElement("BIC");
        FinInstnId1.appendChild(BIC1);
        BIC1.setTextContent("NNBANL2G");
        
        //CdtrAgt
        Element CdtrAgt = doc.createElement("CdtrAgt");
        OrgnlTxRef.appendChild(CdtrAgt);
        Element FinInstnId2 = doc.createElement("FinInstnId");
        CdtrAgt.appendChild(FinInstnId2);
        Element BIC2 = doc.createElement("BIC");
        FinInstnId2.appendChild(BIC2);
        BIC2.setTextContent("RABONL2U");
        
      //Element inside Cdtr
        Element Cdtr1 = doc.createElement("Cdtr");
        OrgnlTxRef.appendChild(Cdtr1);
        Element Nm22 = doc.createElement("Nm");
        Cdtr1.appendChild(Nm22);
        Nm22.setTextContent("Company 0");
        
      //Tag PstlAdr
        Element PstlAdr1 = doc.createElement("PstlAdr");
        Cdtr1.appendChild(PstlAdr1);
        Element Ctry11 = doc.createElement("Ctry");
        PstlAdr1.appendChild(Ctry11);
        Ctry11.setTextContent("NL");
        //<AdrLine>s Gravenhof 2 D</AdrLine>
        //<AdrLine>7301 DN ZUTPHEN</AdrLine>
        Element AdrLine1 = doc.createElement("AdrLine");
        PstlAdr1.appendChild(AdrLine1);
        AdrLine1.setTextContent("Milkyway 0");
        Element AdrLine1_1 = doc.createElement("AdrLine");
        PstlAdr1.appendChild(AdrLine1_1);
        AdrLine1_1.setTextContent("2595 AK Den Haag");
        
        // Element inside  CdtrAcct
        Element CdtrAcct1 = doc.createElement("CdtrAcct");
        OrgnlTxRef.appendChild(CdtrAcct1);
        Element ID6 = doc.createElement("Id");
        CdtrAcct1.appendChild(ID6);
        Element IBAN11 = doc.createElement("IBAN");
        ID6.appendChild(IBAN11);
        IBAN11.setTextContent("NL12RABO0100001213");
        
      //Tag UltmtCdtr
        Element UltmtCdtr3 = doc.createElement("UltmtCdtr");
        OrgnlTxRef.appendChild(UltmtCdtr3);
        Element Nm3_3 = doc.createElement("Nm");
        UltmtCdtr3.appendChild(Nm3_3);
        Nm3_3.setTextContent("Ult creditor 0");
        
        Element Id44 = doc.createElement("Id");
        UltmtCdtr3.appendChild(Id44);
        Element OrgId44 = doc.createElement("OrgId");
        Id44.appendChild(OrgId44);
        Element BICOrBEI44 = doc.createElement("BICOrBEI");
        OrgId44.appendChild(BICOrBEI44);
        BICOrBEI44.setTextContent("RABONL2U");
        
        
        
        
       
       


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("file.xml"));
        transformer.transform(source, result);


    }



}



