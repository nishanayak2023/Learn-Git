package XMLCreation;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.nio.file.Files;
import java.nio.file.Path;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class PACS008 {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootelement = doc.createElement("Document");
        String ppp = "urn:iso:std:iso:20022:tech:xsd:pacs.008.001.08";
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
        Element FIToFICstmrCdtTrf = doc.createElement("FIToFICstmrCdtTrf");
        rootelement.appendChild(FIToFICstmrCdtTrf);

        Element GrpHdr = doc.createElement("GrpHdr");
        FIToFICstmrCdtTrf.appendChild(GrpHdr);

        Element MsgId = doc.createElement("MsgId");
        GrpHdr.appendChild(MsgId);
        MsgId.setTextContent(MsgIdText);
        Element CreDtTm = doc.createElement("CreDtTm");
        GrpHdr.appendChild(CreDtTm);
        CreDtTm.setTextContent(currdate);
        Element NbOfTxs = doc.createElement("NbOfTxs");
        GrpHdr.appendChild(NbOfTxs);
        NbOfTxs.setTextContent("6");
        Element TtlIntrBkSttlmAmt = doc.createElement("TtlIntrBkSttlmAmt");
        GrpHdr.appendChild(TtlIntrBkSttlmAmt);
        TtlIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        TtlIntrBkSttlmAmt.setTextContent("525");
        Element IntrBkSttlmDt = doc.createElement("IntrBkSttlmDt");
        GrpHdr.appendChild(IntrBkSttlmDt);
        IntrBkSttlmDt.setTextContent(OnlyDate);
        
        //Elements inside SttlmInf
        Element SttlmInf = doc.createElement("SttlmInf");
        Element SttlmMtd = doc.createElement("SttlmMtd");
        Element ClrSys = doc.createElement("ClrSys");
        Element Cd = doc.createElement("Cd");

        GrpHdr.appendChild(SttlmInf);
        SttlmInf.appendChild(SttlmMtd);
        SttlmMtd.setTextContent("CLRG");
        SttlmInf.appendChild(ClrSys);
        ClrSys.appendChild(Cd);
        Cd.setTextContent("INC");
        
        //Elemet inside InstdAgt
        Element InstdAgt1 = doc.createElement("InstdAgt");
        Element FinInstnId_SuperTag1 = doc.createElement("FinInstnId");
        Element BICFI = doc.createElement("BICFI");
        GrpHdr.appendChild(InstdAgt1);
        InstdAgt1.appendChild(FinInstnId_SuperTag1);
        FinInstnId_SuperTag1.appendChild(BICFI);
        BICFI.setTextContent("NNBANL2G");
        
        

        Element CdtTrfTxInf1 = doc.createElement("CdtTrfTxInf");
        FIToFICstmrCdtTrf.appendChild(CdtTrfTxInf1);
        Element PmtId1 = doc.createElement("PmtId");
        CdtTrfTxInf1.appendChild(PmtId1);
        Element InstrId = doc.createElement("InstrId");
        PmtId1.appendChild(InstrId);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf1 = doc.createElement("PmtTpInf");
        CdtTrfTxInf1.appendChild(PmtTpInf1);
        Element SvcLvl1 = doc.createElement("SvcLvl");
        PmtTpInf1.appendChild(SvcLvl1);
        Element Cd1 = doc.createElement("Cd");
        SvcLvl1.appendChild(Cd1);
        Cd1.setTextContent("SEPA");
        
        
      
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
        String MsgIdText1 ="INNDNL2U"+Messagetime111 ;
        System.out.println(MsgIdText1);
        InstrId.setTextContent(MsgIdText1);
        Element EndToEndId = doc.createElement("EndToEndId");
        PmtId1.appendChild(EndToEndId);
        String EndToEndIdText = "E2E"+Messagetime111;
        EndToEndId.setTextContent(EndToEndIdText);
        Element TxId = doc.createElement("TxId");
        PmtId1.appendChild(TxId);
        TxId.setTextContent("TxId"+Messagetime111);
        
        
        Element IntrBkSttlmAmt = doc.createElement("IntrBkSttlmAmt");
        CdtTrfTxInf1.appendChild(IntrBkSttlmAmt);
        IntrBkSttlmAmt.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt.setTextContent("101");
        Element ChrgBr = doc.createElement("ChrgBr");
        CdtTrfTxInf1.appendChild(ChrgBr);
        ChrgBr.setTextContent("SLEV");
        
        //Element inside InstgAgt
        Element InstgAgt1 = doc.createElement("InstgAgt");
        CdtTrfTxInf1.appendChild(InstgAgt1);
        Element FinInstnId1= doc.createElement("FinInstnId");
        InstgAgt1.appendChild(FinInstnId1);
        Element BICFI1 = doc.createElement("BICFI");
        FinInstnId1.appendChild(BICFI1);
        BICFI1.setTextContent("INGBNL2A");
        
        //Element inside Dbtr
        Element Dbtr1 = doc.createElement("Dbtr");
        CdtTrfTxInf1.appendChild(Dbtr1);
        Element Nm1 = doc.createElement("Nm");
        Dbtr1.appendChild(Nm1);
        Nm1.setTextContent("Debtor 0");
        //
        
        //Element inside DbtrAcct1
        Element DbtrAcct1 = doc.createElement("DbtrAcct");
        CdtTrfTxInf1.appendChild(DbtrAcct1);
        Element ID1_ = doc.createElement("Id");
        DbtrAcct1.appendChild(ID1_);
        Element IBAN1_ = doc.createElement("IBAN");
        ID1_.appendChild(IBAN1_);
        IBAN1_.setTextContent("NL43INGB7777779928");
        
        //Element inside DbtrAgt
        Element DbtrAgt1 = doc.createElement("DbtrAgt");
        CdtTrfTxInf1.appendChild(DbtrAgt1);
        Element FinInstnId2 = doc.createElement("FinInstnId");
        DbtrAgt1.appendChild(FinInstnId2);
        Element BICFI2 = doc.createElement("BICFI");
        FinInstnId2.appendChild(BICFI2);
        BICFI2.setTextContent("INGBNL2A");
        
        //Element inside CdtrAgt
        Element CdtrAgt = doc.createElement("CdtrAgt");
        CdtTrfTxInf1.appendChild(CdtrAgt);
        Element FinInstnId2_abc = doc.createElement("FinInstnId");
        CdtrAgt.appendChild(FinInstnId2_abc);
        Element BICFI3 = doc.createElement("BICFI");
        FinInstnId2_abc.appendChild(BICFI3);
        BICFI3.setTextContent("NNBANL2G");
        
        //Element inside Cdtr
        Element Cdtr1 = doc.createElement("Cdtr");
        CdtTrfTxInf1.appendChild(Cdtr1);
        Element Nm2 = doc.createElement("Nm");
        Cdtr1.appendChild(Nm2);
        Nm2.setTextContent("Galaxy 0");
        
        // Element inside  CdtrAcct
        Element CdtrAcct1 = doc.createElement("CdtrAcct");
        CdtTrfTxInf1.appendChild(CdtrAcct1);
        Element ID6 = doc.createElement("Id");
        CdtrAcct1.appendChild(ID6);
        Element IBAN1 = doc.createElement("IBAN");
        ID6.appendChild(IBAN1);
        IBAN1.setTextContent("NL97NNBA2022645367");
        
        //ElementInside RmtInf
        Element RmtInf1 = doc.createElement("RmtInf");
        CdtTrfTxInf1.appendChild(RmtInf1);
        Element Ustrd = doc.createElement("Ustrd");
        RmtInf1.appendChild(Ustrd);
        Ustrd.setTextContent("SI starting with 2");
        
        //Amount2
        
        //String Messagetime111 =Messagetime11+"T"+Messagetime21;
      
       
        
        Element CdtTrfTxInf2 = doc.createElement("CdtTrfTxInf");
        FIToFICstmrCdtTrf.appendChild(CdtTrfTxInf2);
        Element PmtId2 = doc.createElement("PmtId");
        CdtTrfTxInf2.appendChild(PmtId2);
        Element InstrId2 = doc.createElement("InstrId");
        PmtId2.appendChild(InstrId2);
        
        String MsgIdText2 ="INNDNL2U"+Messagetime111 ;
        System.out.println(MsgIdText2);
        InstrId2.setTextContent(MsgIdText2);
        Element EndToEndId2 = doc.createElement("EndToEndId");
        PmtId2.appendChild(EndToEndId2);
        String EndToEndIdText2 = "E2E"+Messagetime111;
        EndToEndId2.setTextContent(EndToEndIdText2);
        Element TxId2 = doc.createElement("TxId");
        PmtId2.appendChild(TxId2);
        TxId2.setTextContent("TxId"+Messagetime111);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf2 = doc.createElement("PmtTpInf");
        CdtTrfTxInf2.appendChild(PmtTpInf2);
        Element SvcLvl2 = doc.createElement("SvcLvl");
        PmtTpInf2.appendChild(SvcLvl2);
        Element Cd2 = doc.createElement("Cd");
        SvcLvl2.appendChild(Cd2);
        Cd2.setTextContent("SEPA");
        
        
      
       
        
        
        Element IntrBkSttlmAmt2 = doc.createElement("IntrBkSttlmAmt");
        CdtTrfTxInf2.appendChild(IntrBkSttlmAmt2);
        IntrBkSttlmAmt2.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt2.setTextContent("102");
        Element ChrgBr2 = doc.createElement("ChrgBr");
        CdtTrfTxInf2.appendChild(ChrgBr2);
        ChrgBr2.setTextContent("SLEV");
        
        //Element inside InstgAgt
        Element InstgAgt2 = doc.createElement("InstgAgt");
        CdtTrfTxInf2.appendChild(InstgAgt2);
        Element FinInstnId2_2= doc.createElement("FinInstnId");
        InstgAgt2.appendChild(FinInstnId2_2);
        Element BICFI2_2 = doc.createElement("BICFI");
        FinInstnId2_2.appendChild(BICFI2_2);
        BICFI2_2.setTextContent("DEUTESBB");
        
        //Element inside Dbtr
        Element Dbtr2 = doc.createElement("Dbtr");
        CdtTrfTxInf2.appendChild(Dbtr2);
        Element Nm2_2 = doc.createElement("Nm");
        Dbtr2.appendChild(Nm2_2);
        Nm2_2.setTextContent("Debtor 1");
        
        
        //Element inside 
        Element DbtrAcct2 = doc.createElement("DbtrAcct");
        CdtTrfTxInf2.appendChild(DbtrAcct2);
        Element ID2_ = doc.createElement("Id");
        DbtrAcct2.appendChild(ID2_);
        Element IBAN2_ = doc.createElement("IBAN");
        ID2_.appendChild(IBAN2_);
        IBAN2_.setTextContent("ES3300190029114010023693");
        
        //Element inside DbtrAgt
        Element DbtrAgt2 = doc.createElement("DbtrAgt");
        CdtTrfTxInf2.appendChild(DbtrAgt2);
        Element FinInstnId2New = doc.createElement("FinInstnId");
        DbtrAgt2.appendChild(FinInstnId2New);
        Element BICFI2New = doc.createElement("BICFI");
        FinInstnId2New.appendChild(BICFI2New);
        BICFI2New.setTextContent("DEUTESBB");
        
        //Element inside CdtrAgt
        Element CdtrAgt2New = doc.createElement("CdtrAgt");
        CdtTrfTxInf2.appendChild(CdtrAgt2New);
        Element FinInstnIdLatest2 = doc.createElement("FinInstnId");
        CdtrAgt2New.appendChild(FinInstnIdLatest2);
        Element BICFILatest2 = doc.createElement("BICFI");
        FinInstnIdLatest2.appendChild(BICFILatest2);
        BICFILatest2.setTextContent("NNBANL2G");
        
        //Element inside Cdtr
        Element Cdtr2 = doc.createElement("Cdtr");
        CdtTrfTxInf2.appendChild(Cdtr2);
        Element Nm2_Latest = doc.createElement("Nm");
        Cdtr2.appendChild(Nm2_Latest);
        Nm2_Latest.setTextContent("Galaxy 1");
        
        // Element inside  CdtrAcct
        Element CdtrAcct2 = doc.createElement("CdtrAcct");
        CdtTrfTxInf2.appendChild(CdtrAcct2);
        Element ID2_New = doc.createElement("Id");
        CdtrAcct2.appendChild(ID2_New);
        Element IBAN2 = doc.createElement("IBAN");
        ID2_New.appendChild(IBAN2);
        IBAN2.setTextContent("NL02NNBA3188204897");
        
        //ElementInside RmtInf
        Element RmtInf2 = doc.createElement("RmtInf");
        CdtTrfTxInf2.appendChild(RmtInf2);
        Element Ustrd2 = doc.createElement("Ustrd");
        RmtInf2.appendChild(Ustrd2);
        Ustrd2.setTextContent("SI starting with 3");
        
 //Amount3
        
        //String Messagetime111 =Messagetime11+"T"+Messagetime21;
      
       
        
        Element CdtTrfTxInf3 = doc.createElement("CdtTrfTxInf");
        FIToFICstmrCdtTrf.appendChild(CdtTrfTxInf3);
        Element PmtId3 = doc.createElement("PmtId");
        CdtTrfTxInf3.appendChild(PmtId3);
        Element InstrId3 = doc.createElement("InstrId");
        PmtId3.appendChild(InstrId3);
        
        String MsgIdText3 ="INNDNL2U"+Messagetime111 ;
        System.out.println(MsgIdText3);
        InstrId3.setTextContent(MsgIdText3);
        Element EndToEndId3 = doc.createElement("EndToEndId");
        PmtId3.appendChild(EndToEndId3);
        String EndToEndIdText3 = "E2E"+Messagetime111;
        EndToEndId3.setTextContent(EndToEndIdText3);
        Element TxId3 = doc.createElement("TxId");
        PmtId3.appendChild(TxId3);
        TxId3.setTextContent("TxId"+Messagetime111);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf3 = doc.createElement("PmtTpInf");
        CdtTrfTxInf3.appendChild(PmtTpInf3);
        Element SvcLvl3 = doc.createElement("SvcLvl");
        PmtTpInf3.appendChild(SvcLvl3);
        Element Cd3 = doc.createElement("Cd");
        SvcLvl3.appendChild(Cd3);
        Cd3.setTextContent("SEPA");
        
        
      
       
        
        
        Element IntrBkSttlmAmt3 = doc.createElement("IntrBkSttlmAmt");
        CdtTrfTxInf3.appendChild(IntrBkSttlmAmt3);
        IntrBkSttlmAmt3.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt3.setTextContent("103");
        Element ChrgBr3 = doc.createElement("ChrgBr");
        CdtTrfTxInf3.appendChild(ChrgBr3);
        ChrgBr3.setTextContent("SLEV");
        
        //Element inside InstgAgt
        Element InstgAgt3 = doc.createElement("InstgAgt");
        CdtTrfTxInf3.appendChild(InstgAgt3);
        Element FinInstnId3_3= doc.createElement("FinInstnId");
        InstgAgt3.appendChild(FinInstnId3_3);
        Element BICFI3_3 = doc.createElement("BICFI");
        FinInstnId3_3.appendChild(BICFI3_3);
        BICFI3_3.setTextContent("DEUTESBB");
        
        //Element inside Dbtr
        Element Dbtr3 = doc.createElement("Dbtr");
        CdtTrfTxInf3.appendChild(Dbtr3);
        Element Nm3_3 = doc.createElement("Nm");
        Dbtr3.appendChild(Nm3_3);
        Nm3_3.setTextContent("Debtor 2");
        
        
        //Element inside 
        Element DbtrAcct3 = doc.createElement("DbtrAcct");
        CdtTrfTxInf3.appendChild(DbtrAcct3);
        Element ID3_ = doc.createElement("Id");
        DbtrAcct3.appendChild(ID3_);
        Element IBAN3_ = doc.createElement("IBAN");
        ID3_.appendChild(IBAN3_);
        IBAN3_.setTextContent("ES3300190029114010023693");
        
        //Element inside DbtrAgt
        Element DbtrAgt3 = doc.createElement("DbtrAgt");
        CdtTrfTxInf3.appendChild(DbtrAgt3);
        Element FinInstnId3New = doc.createElement("FinInstnId");
        DbtrAgt3.appendChild(FinInstnId3New);
        Element BICFI3New = doc.createElement("BICFI");
        FinInstnId3New.appendChild(BICFI3New);
        BICFI3New.setTextContent("DEUTESBB");
        
        //Element inside CdtrAgt
        Element CdtrAgt3New = doc.createElement("CdtrAgt");
        CdtTrfTxInf3.appendChild(CdtrAgt3New);
        Element FinInstnIdLatest3 = doc.createElement("FinInstnId");
        CdtrAgt3New.appendChild(FinInstnIdLatest3);
        Element BICFILatest3 = doc.createElement("BICFI");
        FinInstnIdLatest3.appendChild(BICFILatest3);
        BICFILatest3.setTextContent("NNBANL2G");
        
        //Element inside Cdtr
        Element Cdtr3 = doc.createElement("Cdtr");
        CdtTrfTxInf3.appendChild(Cdtr3);
        Element Nm3_Latest = doc.createElement("Nm");
        Cdtr3.appendChild(Nm3_Latest);
        Nm3_Latest.setTextContent("Galaxy 2");
        
        // Element inside  CdtrAcct
        Element CdtrAcct3 = doc.createElement("CdtrAcct");
        CdtTrfTxInf3.appendChild(CdtrAcct3);
        Element ID3_New = doc.createElement("Id");
        CdtrAcct3.appendChild(ID3_New);
        Element IBAN3 = doc.createElement("IBAN");
        ID3_New.appendChild(IBAN3);
        IBAN3.setTextContent("NL54NNBA2040114580");
        
        //ElementInside RmtInf
        Element RmtInf3 = doc.createElement("RmtInf");
        CdtTrfTxInf3.appendChild(RmtInf3);
        Element Ustrd3 = doc.createElement("Ustrd");
        RmtInf3.appendChild(Ustrd3);
        Ustrd3.setTextContent("SI Blocked");
        
 //Amount4
        
        //String Messagetime111 =Messagetime11+"T"+Messagetime21;
      
       
        
        Element CdtTrfTxInf4 = doc.createElement("CdtTrfTxInf");
        FIToFICstmrCdtTrf.appendChild(CdtTrfTxInf4);
        Element PmtId4 = doc.createElement("PmtId");
        CdtTrfTxInf4.appendChild(PmtId4);
        Element InstrId4 = doc.createElement("InstrId");
        PmtId4.appendChild(InstrId4);
        
        String MsgIdText4 ="INNDNL2U"+Messagetime111 ;
        System.out.println(MsgIdText4);
        InstrId4.setTextContent(MsgIdText4);
        Element EndToEndId4 = doc.createElement("EndToEndId");
        PmtId4.appendChild(EndToEndId4);
        String EndToEndIdText4 = "E2E"+Messagetime111;
        EndToEndId4.setTextContent(EndToEndIdText4);
        Element TxId4 = doc.createElement("TxId");
        PmtId4.appendChild(TxId4);
        TxId4.setTextContent("TxId"+Messagetime111);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf4 = doc.createElement("PmtTpInf");
        CdtTrfTxInf4.appendChild(PmtTpInf4);
        Element SvcLvl4 = doc.createElement("SvcLvl");
        PmtTpInf4.appendChild(SvcLvl4);
        Element Cd4 = doc.createElement("Cd");
        SvcLvl4.appendChild(Cd4);
        Cd4.setTextContent("SEPA");
        
        
      
       
        
        
        Element IntrBkSttlmAmt4 = doc.createElement("IntrBkSttlmAmt");
        CdtTrfTxInf4.appendChild(IntrBkSttlmAmt4);
        IntrBkSttlmAmt4.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt4.setTextContent("104");
        Element ChrgBr4 = doc.createElement("ChrgBr");
        CdtTrfTxInf4.appendChild(ChrgBr4);
        ChrgBr4.setTextContent("SLEV");
        
        //Element inside InstgAgt
        Element InstgAgt4 = doc.createElement("InstgAgt");
        CdtTrfTxInf4.appendChild(InstgAgt4);
        Element FinInstnId4_4= doc.createElement("FinInstnId");
        InstgAgt4.appendChild(FinInstnId4_4);
        Element BICFI4_4 = doc.createElement("BICFI");
        FinInstnId4_4.appendChild(BICFI4_4);
        BICFI4_4.setTextContent("RABONL2U");
        
        //Element inside Dbtr
        Element Dbtr4 = doc.createElement("Dbtr");
        CdtTrfTxInf4.appendChild(Dbtr4);
        Element Nm4_4 = doc.createElement("Nm");
        Dbtr4.appendChild(Nm4_4);
        Nm4_4.setTextContent("Debtor 3");
        
        
        //Element inside 
        Element DbtrAcct4 = doc.createElement("DbtrAcct");
        CdtTrfTxInf4.appendChild(DbtrAcct4);
        Element ID4_ = doc.createElement("Id");
        DbtrAcct4.appendChild(ID4_);
        Element IBAN4_ = doc.createElement("IBAN");
        ID4_.appendChild(IBAN4_);
        IBAN4_.setTextContent("NL12RABO0100001213");
        
        //Element inside DbtrAgt
        Element DbtrAgt4 = doc.createElement("DbtrAgt");
        CdtTrfTxInf4.appendChild(DbtrAgt4);
        Element FinInstnId4New = doc.createElement("FinInstnId");
        DbtrAgt4.appendChild(FinInstnId4New);
        Element BICFI4New = doc.createElement("BICFI");
        FinInstnId4New.appendChild(BICFI4New);
        BICFI4New.setTextContent("RABONL2U");
        
        //Element inside CdtrAgt
        Element CdtrAgt4New = doc.createElement("CdtrAgt");
        CdtTrfTxInf4.appendChild(CdtrAgt4New);
        Element FinInstnIdLatest4 = doc.createElement("FinInstnId");
        CdtrAgt4New.appendChild(FinInstnIdLatest4);
        Element BICFILatest4 = doc.createElement("BICFI");
        FinInstnIdLatest4.appendChild(BICFILatest4);
        BICFILatest4.setTextContent("NNBANL2G");
        
        //Element inside Cdtr
        Element Cdtr4 = doc.createElement("Cdtr");
        CdtTrfTxInf4.appendChild(Cdtr4);
        Element Nm4_Latest = doc.createElement("Nm");
        Cdtr4.appendChild(Nm4_Latest);
        Nm4_Latest.setTextContent("Galaxy 3");
        
        // Element inside  CdtrAcct
        Element CdtrAcct4 = doc.createElement("CdtrAcct");
        CdtTrfTxInf4.appendChild(CdtrAcct4);
        Element ID4_New = doc.createElement("Id");
        CdtrAcct4.appendChild(ID4_New);
        Element IBAN4 = doc.createElement("IBAN");
        ID4_New.appendChild(IBAN4);
        IBAN4.setTextContent("NL61NNBA0719053412");
        
        //ElementInside RmtInf
        Element RmtInf4 = doc.createElement("RmtInf");
        CdtTrfTxInf4.appendChild(RmtInf4);
        Element Ustrd4 = doc.createElement("Ustrd");
        RmtInf4.appendChild(Ustrd4);
        Ustrd4.setTextContent("SI starting with 0");  
        
        
//Amount5
        
        //String Messagetime111 =Messagetime11+"T"+Messagetime21;
      
       
        
        Element CdtTrfTxInf5 = doc.createElement("CdtTrfTxInf");
        FIToFICstmrCdtTrf.appendChild(CdtTrfTxInf5);
        Element PmtId5 = doc.createElement("PmtId");
        CdtTrfTxInf5.appendChild(PmtId5);
        Element InstrId5 = doc.createElement("InstrId");
        PmtId5.appendChild(InstrId5);
        
        String MsgIdText5 ="INNDNL2U"+Messagetime111 ;
        System.out.println(MsgIdText5);
        InstrId5.setTextContent(MsgIdText5);
        Element EndToEndId5 = doc.createElement("EndToEndId");
        PmtId5.appendChild(EndToEndId5);
        String EndToEndIdText5 = "E2E"+Messagetime111;
        EndToEndId5.setTextContent(EndToEndIdText5);
        Element TxId5 = doc.createElement("TxId");
        PmtId5.appendChild(TxId5);
        TxId5.setTextContent("TxId"+Messagetime111);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf5 = doc.createElement("PmtTpInf");
        CdtTrfTxInf5.appendChild(PmtTpInf5);
        Element SvcLvl5 = doc.createElement("SvcLvl");
        PmtTpInf5.appendChild(SvcLvl5);
        Element Cd5 = doc.createElement("Cd");
        SvcLvl5.appendChild(Cd5);
        Cd5.setTextContent("SEPA");
        
        
      
       
        
        
        Element IntrBkSttlmAmt5 = doc.createElement("IntrBkSttlmAmt");
        CdtTrfTxInf5.appendChild(IntrBkSttlmAmt5);
        IntrBkSttlmAmt5.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt5.setTextContent("105");
        Element ChrgBr5 = doc.createElement("ChrgBr");
        CdtTrfTxInf5.appendChild(ChrgBr5);
        ChrgBr5.setTextContent("SLEV");
        
        //Element inside InstgAgt
        Element InstgAgt5 = doc.createElement("InstgAgt");
        CdtTrfTxInf5.appendChild(InstgAgt5);
        Element FinInstnId5_5= doc.createElement("FinInstnId");
        InstgAgt5.appendChild(FinInstnId5_5);
        Element BICFI5_5 = doc.createElement("BICFI");
        FinInstnId5_5.appendChild(BICFI5_5);
        BICFI5_5.setTextContent("DEUTESBB");
        
        //Element inside Dbtr
        Element Dbtr5 = doc.createElement("Dbtr");
        CdtTrfTxInf5.appendChild(Dbtr5);
        Element Nm5_5 = doc.createElement("Nm");
        Dbtr5.appendChild(Nm5_5);
        Nm5_5.setTextContent("Debtor 4");
        
        
        //Element inside 
        Element DbtrAcct5 = doc.createElement("DbtrAcct");
        CdtTrfTxInf5.appendChild(DbtrAcct5);
        Element ID5_ = doc.createElement("Id");
        DbtrAcct5.appendChild(ID5_);
        Element IBAN5_ = doc.createElement("IBAN");
        ID5_.appendChild(IBAN5_);
        IBAN5_.setTextContent("ES3300190029114010023693");
        
        //Element inside DbtrAgt
        Element DbtrAgt5 = doc.createElement("DbtrAgt");
        CdtTrfTxInf5.appendChild(DbtrAgt5);
        Element FinInstnId5New = doc.createElement("FinInstnId");
        DbtrAgt5.appendChild(FinInstnId5New);
        Element BICFI5New = doc.createElement("BICFI");
        FinInstnId5New.appendChild(BICFI5New);
        BICFI5New.setTextContent("DEUTESBB");
        
        //Element inside CdtrAgt
        Element CdtrAgt5New = doc.createElement("CdtrAgt");
        CdtTrfTxInf5.appendChild(CdtrAgt5New);
        Element FinInstnIdLatest5 = doc.createElement("FinInstnId");
        CdtrAgt5New.appendChild(FinInstnIdLatest5);
        Element BICFILatest5 = doc.createElement("BICFI");
        FinInstnIdLatest5.appendChild(BICFILatest5);
        BICFILatest5.setTextContent("NNBANL2G");
        
        //Element inside Cdtr
        Element Cdtr5 = doc.createElement("Cdtr");
        CdtTrfTxInf5.appendChild(Cdtr5);
        Element Nm5_Latest = doc.createElement("Nm");
        Cdtr5.appendChild(Nm5_Latest);
        Nm5_Latest.setTextContent("Galaxy 4");
        
        // Element inside  CdtrAcct
        Element CdtrAcct5 = doc.createElement("CdtrAcct");
        CdtTrfTxInf5.appendChild(CdtrAcct5);
        Element ID5_New = doc.createElement("Id");
        CdtrAcct5.appendChild(ID5_New);
        Element IBAN5 = doc.createElement("IBAN");
        ID5_New.appendChild(IBAN5);
        IBAN5.setTextContent("NL21NNBA1000000567");
        
        //ElementInside RmtInf
        Element RmtInf5 = doc.createElement("RmtInf");
        CdtTrfTxInf5.appendChild(RmtInf5);
        Element Ustrd5 = doc.createElement("Ustrd");
        RmtInf5.appendChild(Ustrd5);
        Ustrd5.setTextContent("CA starting with ");  
        
//Amount6
        
        //String Messagetime111 =Messagetime11+"T"+Messagetime21;
      
       
        
        Element CdtTrfTxInf6 = doc.createElement("CdtTrfTxInf");
        FIToFICstmrCdtTrf.appendChild(CdtTrfTxInf6);
        Element PmtId6 = doc.createElement("PmtId");
        CdtTrfTxInf6.appendChild(PmtId6);
        Element InstrId6 = doc.createElement("InstrId");
        PmtId6.appendChild(InstrId6);
        
        String MsgIdText6 ="INNDNL2U"+Messagetime111 ;
        System.out.println(MsgIdText6);
        InstrId6.setTextContent(MsgIdText6);
        Element EndToEndId6 = doc.createElement("EndToEndId");
        PmtId6.appendChild(EndToEndId6);
        String EndToEndIdText6 = "E2E"+Messagetime111;
        EndToEndId6.setTextContent(EndToEndIdText6);
        Element TxId6 = doc.createElement("TxId");
        PmtId6.appendChild(TxId6);
        TxId6.setTextContent("TxId"+Messagetime111);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf6 = doc.createElement("PmtTpInf");
        CdtTrfTxInf6.appendChild(PmtTpInf6);
        Element SvcLvl6 = doc.createElement("SvcLvl");
        PmtTpInf6.appendChild(SvcLvl6);
        Element Cd6 = doc.createElement("Cd");
        SvcLvl6.appendChild(Cd6);
        Cd6.setTextContent("SEPA");
        
        
      
       
        
        
        Element IntrBkSttlmAmt6 = doc.createElement("IntrBkSttlmAmt");
        CdtTrfTxInf6.appendChild(IntrBkSttlmAmt6);
        IntrBkSttlmAmt6.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt6.setTextContent("10.00");
        Element ChrgBr6 = doc.createElement("ChrgBr");
        CdtTrfTxInf6.appendChild(ChrgBr6);
        ChrgBr6.setTextContent("SLEV");
        
        //Element inside InstgAgt
        Element InstgAgt6 = doc.createElement("InstgAgt");
        CdtTrfTxInf6.appendChild(InstgAgt6);
        Element FinInstnId6_6= doc.createElement("FinInstnId");
        InstgAgt6.appendChild(FinInstnId6_6);
        Element BICFI6_6 = doc.createElement("BICFI");
        FinInstnId6_6.appendChild(BICFI6_6);
        BICFI6_6.setTextContent("DEUTESBB");
        
        //Element inside Dbtr
        Element Dbtr6 = doc.createElement("Dbtr");
        CdtTrfTxInf6.appendChild(Dbtr6);
        Element Nm6_6 = doc.createElement("Nm");
        Dbtr6.appendChild(Nm6_6);
        Nm6_6.setTextContent("Debtor 0");
        
        
        //Element inside 
        Element DbtrAcct6 = doc.createElement("DbtrAcct");
        CdtTrfTxInf6.appendChild(DbtrAcct6);
        Element ID6_ = doc.createElement("Id");
        DbtrAcct6.appendChild(ID6_);
        Element IBAN6_ = doc.createElement("IBAN");
        ID6_.appendChild(IBAN6_);
        IBAN6_.setTextContent("ES3300190029114010023693");
        
        //Element inside DbtrAgt
        Element DbtrAgt6 = doc.createElement("DbtrAgt");
        CdtTrfTxInf6.appendChild(DbtrAgt6);
        Element FinInstnId6New = doc.createElement("FinInstnId");
        DbtrAgt6.appendChild(FinInstnId6New);
        Element BICFI6New = doc.createElement("BICFI");
        FinInstnId6New.appendChild(BICFI6New);
        BICFI6New.setTextContent("DEUTESBB");
        
        //Element inside CdtrAgt
        Element CdtrAgt6New = doc.createElement("CdtrAgt");
        CdtTrfTxInf6.appendChild(CdtrAgt6New);
        Element FinInstnIdLatest6 = doc.createElement("FinInstnId");
        CdtrAgt6New.appendChild(FinInstnIdLatest6);
        Element BICFILatest6 = doc.createElement("BICFI");
        FinInstnIdLatest6.appendChild(BICFILatest6);
        BICFILatest6.setTextContent("NNBANL2G");
        
        //Element inside Cdtr
        Element Cdtr6 = doc.createElement("Cdtr");
        CdtTrfTxInf6.appendChild(Cdtr6);
        Element Nm6_Latest = doc.createElement("Nm");
        Cdtr6.appendChild(Nm6_Latest);
        Nm6_Latest.setTextContent("Galaxy 0");
        
        // Element inside  CdtrAcct
        Element CdtrAcct6 = doc.createElement("CdtrAcct");
        CdtTrfTxInf6.appendChild(CdtrAcct6);
        Element ID6_New = doc.createElement("Id");
        CdtrAcct6.appendChild(ID6_New);
        Element IBAN6 = doc.createElement("IBAN");
        ID6_New.appendChild(IBAN6);
        IBAN6.setTextContent("NL46NNBA4000000408");
        
        //ElementInside RmtInf
        Element RmtInf6 = doc.createElement("RmtInf");
        CdtTrfTxInf6.appendChild(RmtInf6);
        Element Ustrd6 = doc.createElement("Ustrd");
        RmtInf6.appendChild(Ustrd6);
        Ustrd6.setTextContent("CA starting with 4");  

        
        
       
       


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("PACS00813.xml"));
        transformer.transform(source, result);


    }



}



