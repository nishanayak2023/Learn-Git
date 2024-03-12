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


public class PACS003FutureDate {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootelement = doc.createElement("Document");
        String ppp = "urn:iso:std:iso:20022:tech:xsd:pacs.003.001.08";
        String bbb = "http://www.w3.org/2001/XMLSchema-instance";
        rootelement.setAttribute("xmlns", ppp);
        rootelement.setAttribute("xmlns:xsi", bbb);
        doc.appendChild(rootelement);
        int leftLimit = 97; // letter 'a'
        int rightLimit = 133; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String TempMsgIdText = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(TempMsgIdText);
        Date date = new Date();
     // Get the current date
        Date currentDate = new Date();

        // Add one day using Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date nextDay = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat aaa = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ccc = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat ddd = new SimpleDateFormat("HHmmss");
        String formattedDate = sdf.format(date);
        //String formattedNextDay = aaa.format(date);
        String formattedNextDay = aaa.format(nextDay);
        System.out.println("NextDate" +formattedNextDay);
        String currdate = formattedDate.replace(" ", "T");
        String Messagetime1 =ccc.format(date);
        String Messagetime3 =ddd.format(date);
        String Messagetime =Messagetime1+"T"+Messagetime3;
        System.out.println(currdate);
        System.out.println(formattedNextDay);
        System.out.println(Messagetime);
        String MsgIdText ="INNDNL3U"+Messagetime ;
        System.out.println(MsgIdText);
        
        //Adding elements to XML
        Element FIToFICstmrDrctDbt = doc.createElement("FIToFICstmrDrctDbt");
        rootelement.appendChild(FIToFICstmrDrctDbt);

        Element GrpHdr = doc.createElement("GrpHdr");
        FIToFICstmrDrctDbt.appendChild(GrpHdr);

        Element MsgId = doc.createElement("MsgId");
        GrpHdr.appendChild(MsgId);
        MsgId.setTextContent(MsgIdText);
        Element CreDtTm = doc.createElement("CreDtTm");
        GrpHdr.appendChild(CreDtTm);
        CreDtTm.setTextContent(currdate);
        Element NbOfTxs = doc.createElement("NbOfTxs");
        GrpHdr.appendChild(NbOfTxs);
        NbOfTxs.setTextContent("10");
        Element TtlIntrBkSttlmAmt = doc.createElement("TtlIntrBkSttlmAmt");
        GrpHdr.appendChild(TtlIntrBkSttlmAmt);
        TtlIntrBkSttlmAmt.setAttribute("Ccy","EUR");
        TtlIntrBkSttlmAmt.setTextContent("155");
        Element IntrBkSttlmDt = doc.createElement("IntrBkSttlmDt");
        GrpHdr.appendChild(IntrBkSttlmDt);
        IntrBkSttlmDt.setTextContent(formattedNextDay);
        
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
        Element BICFI1 = doc.createElement("BICFI");
        GrpHdr.appendChild(InstdAgt1);
        InstdAgt1.appendChild(FinInstnId_SuperTag1);
        FinInstnId_SuperTag1.appendChild(BICFI1);
        BICFI1.setTextContent("NNBANL2G");
        
        

        Element DrctDbtTxInf1 = doc.createElement("DrctDbtTxInf");
        FIToFICstmrDrctDbt.appendChild(DrctDbtTxInf1);
        Element PmtId1 = doc.createElement("PmtId");
        DrctDbtTxInf1.appendChild(PmtId1);
        Element InstrId = doc.createElement("InstrId");
        PmtId1.appendChild(InstrId);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf1 = doc.createElement("PmtTpInf");
        DrctDbtTxInf1.appendChild(PmtTpInf1);
        Element SvcLvl1 = doc.createElement("SvcLvl");
        PmtTpInf1.appendChild(SvcLvl1);
        Element Cd1 = doc.createElement("Cd");
        SvcLvl1.appendChild(Cd1);
        Cd1.setTextContent("SEPA");
        
        //tag LclInstrm
        
        //Element PmtTpInf1 = doc.createElement("PmtTpInf");
        //DrctDbtTxInf1.appendChild(PmtTpInf1);
        Element LclInstrm1 = doc.createElement("LclInstrm");
        PmtTpInf1.appendChild(LclInstrm1);
        Element Cd1_ = doc.createElement("Cd");
        LclInstrm1.appendChild(Cd1_);
        Cd1_.setTextContent("CORE");
        Element SeqTp1 = doc.createElement("SeqTp");
        PmtTpInf1.appendChild(SeqTp1);
        SeqTp1.setTextContent("RCUR");
        
        //tag CtgyPurp
        Element CtgyPurp1 = doc.createElement("CtgyPurp");
        PmtTpInf1.appendChild(CtgyPurp1);
        Element Cd1_1 = doc.createElement("Cd");
        CtgyPurp1.appendChild(Cd1_1);
        Cd1_1.setTextContent("str1");
        
      
        
        
      
        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat aaa1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat ccc1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat ddd1 = new SimpleDateFormat("HHmmss");
        String formattedDate1 = sdf1.format(date);
        String formattedNextDay1 = aaa1.format(date);
        String currdate1 = formattedDate.replace(" ", "T");
        String Messagetime11 =ccc1.format(date);
        String Messagetime31 =ddd1.format(date);
        String Messagetime111 =Messagetime11+"T"+Messagetime31;
        String MsgIdText1 ="INNDNL3U"+Messagetime111 ;
        System.out.println(MsgIdText1);
        InstrId.setTextContent(MsgIdText1);
        Element EndToEndId1 = doc.createElement("EndToEndId");
        PmtId1.appendChild(EndToEndId1);
        String EndToEndIdText = "E2E"+Messagetime111;
        EndToEndId1.setTextContent(EndToEndIdText);
        Element TxId1 = doc.createElement("TxId");
        PmtId1.appendChild(TxId1);
        TxId1.setTextContent("TXDIDI3A300833F"+Messagetime111);
        
        
        Element IntrBkSttlmAmt1 = doc.createElement("IntrBkSttlmAmt");
        DrctDbtTxInf1.appendChild(IntrBkSttlmAmt1);
        IntrBkSttlmAmt1.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt1.setTextContent("11");
        Element ChrgBr1 = doc.createElement("ChrgBr");
        DrctDbtTxInf1.appendChild(ChrgBr1);
        ChrgBr1.setTextContent("SLEV");
        
        //ReqdColltnDt
        Element ReqdColltnDt1 = doc.createElement("ReqdColltnDt");
        DrctDbtTxInf1.appendChild(ReqdColltnDt1);
        ReqdColltnDt1.setTextContent(formattedNextDay);
        
        
        Element DrctDbtTx1 = doc.createElement("DrctDbtTx");
        DrctDbtTxInf1.appendChild(DrctDbtTx1);
        Element MndtRltdInf1 = doc.createElement("MndtRltdInf");
        DrctDbtTx1.appendChild(MndtRltdInf1);
        Element MndtId1 = doc.createElement("MndtId");
        MndtRltdInf1.appendChild(MndtId1);
        MndtId1.setTextContent("41011");
        
        Element DtOfSgntr1 = doc.createElement("DtOfSgntr");
        MndtRltdInf1.appendChild(DtOfSgntr1);
        DtOfSgntr1.setTextContent(formattedNextDay);
        
        Element AmdmntInd1 = doc.createElement("AmdmntInd");
        MndtRltdInf1.appendChild(AmdmntInd1);
        AmdmntInd1.setTextContent("true");
        
        
        //AmdmntInfDtls
        Element AmdmntInfDtls1 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf1.appendChild(AmdmntInfDtls1);
        Element OrgnlCdtrSchmeId1 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls1.appendChild(OrgnlCdtrSchmeId1);
        Element Nm1 = doc.createElement("Nm");
        OrgnlCdtrSchmeId1.appendChild(Nm1);
        Nm1.setTextContent("Vodafone Co");
        
        Element Id1 = doc.createElement("Id");
        OrgnlCdtrSchmeId1.appendChild(Id1);
        //PrvtId
        
        Element PrvtId1 = doc.createElement("PrvtId");
        Id1.appendChild(PrvtId1);
        //Othr
        Element Othr1 = doc.createElement("Othr");
        PrvtId1.appendChild(Othr1);
        Element Id1_1 = doc.createElement("Id");
        Othr1.appendChild(Id1_1);
        Id1_1.setTextContent("NL16ZZZ330750030000");
        
       
        //SchmeNm
        Element SchmeNm1 = doc.createElement("SchmeNm");
        Othr1.appendChild(SchmeNm1);
        Element Prtry1 = doc.createElement("Prtry");
        SchmeNm1.appendChild(Prtry1);
        Prtry1.setTextContent("SEPA");
        
        
        //OrgnlDbtrAcct
        Element OrgnlDbtrAcct1 = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls1.appendChild(OrgnlDbtrAcct1);
        Element Id_1_1_1 = doc.createElement("Id");
        OrgnlDbtrAcct1.appendChild(Id_1_1_1);
        Element IBAN1__1 = doc.createElement("IBAN");
        Id_1_1_1.appendChild(IBAN1__1);
        IBAN1__1.setTextContent("NL61NNBA0719053813");
        
        //Tag ElctrncSgntr1
        Element ElctrncSgntr1 = doc.createElement("ElctrncSgntr");
        MndtRltdInf1.appendChild(ElctrncSgntr1);
        ElctrncSgntr1.setTextContent("DIANA");
        
        //Tag CdtrSchmeId
        Element CdtrSchmeId1 = doc.createElement("CdtrSchmeId");
        DrctDbtTx1.appendChild(CdtrSchmeId1);
        Element Id_New_1 = doc.createElement("Id");
        CdtrSchmeId1.appendChild(Id_New_1);
        Element PrvtId1_1 = doc.createElement("PrvtId");
        Id_New_1.appendChild(PrvtId1_1);
        Element Othr_1 = doc.createElement("Othr");
        PrvtId1_1.appendChild(Othr_1);
        Element Id_New_1_1 = doc.createElement("Id");
        Othr_1.appendChild(Id_New_1_1);
        Id_New_1_1.setTextContent("NL16ZZZ330750030000");
        

        Element SchmeNm1_1 = doc.createElement("SchmeNm");
        Othr_1.appendChild(SchmeNm1_1);
        Element Prtry1_1 = doc.createElement("Prtry");
        SchmeNm1_1.appendChild(Prtry1_1);
        Prtry1_1.setTextContent("SEPA");
        
        
        
        
        //Tag Cdtr
        Element Cdtr1 = doc.createElement("Cdtr");
        DrctDbtTxInf1.appendChild(Cdtr1);
        Element Nm1_New = doc.createElement("Nm");
        Cdtr1.appendChild(Nm1_New);
        Nm1_New.setTextContent("Vodafon Co");
        
        //Tag PstlAdr
        Element PstlAdr1 = doc.createElement("PstlAdr");
        Cdtr1.appendChild(PstlAdr1);
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
        AdrLine1_1.setTextContent("7201 DN ZUTPHEN");
        
        
        
        //Tag CdtrAcct
        Element CdtrAcct1 = doc.createElement("CdtrAcct");
        DrctDbtTxInf1.appendChild(CdtrAcct1);
        Element Id_1_Acct = doc.createElement("Id");
        CdtrAcct1.appendChild(Id_1_Acct);
        Element IBAN_1 = doc.createElement("IBAN");
        Id_1_Acct.appendChild(IBAN_1);
        IBAN_1.setTextContent("NL12RABO0100001213");
        
        //Tag CdtrAgt
        Element CdtrAgt1 = doc.createElement("CdtrAgt");
        DrctDbtTxInf1.appendChild(CdtrAgt1);
        Element FinInstnId1 = doc.createElement("FinInstnId");
        CdtrAgt1.appendChild(FinInstnId1);
        Element BICFI1_New = doc.createElement("BICFI");
        FinInstnId1.appendChild(BICFI1_New);
        BICFI1_New.setTextContent("RABONL2U");
        
        //Tag UltmtCdtr
        Element UltmtCdtr1 = doc.createElement("UltmtCdtr");
        DrctDbtTxInf1.appendChild(UltmtCdtr1);
        Element Nm1_1 = doc.createElement("Nm");
        UltmtCdtr1.appendChild(Nm1_1);
        Nm1_1.setTextContent("str1234");
        
        //Tag PstlAdr
        Element PstlAdr1_1 = doc.createElement("PstlAdr");
        UltmtCdtr1.appendChild(PstlAdr1_1);
        Element Dept1 = doc.createElement("Dept");
        PstlAdr1_1.appendChild(Dept1);
        Dept1.setTextContent("str1234");
        
        //Tag SubDept
        Element SubDept1 = doc.createElement("SubDept");
        PstlAdr1_1.appendChild(SubDept1);
        SubDept1.setTextContent("str1234");
        
        Element StrtNm1 = doc.createElement("StrtNm");
        PstlAdr1_1.appendChild(StrtNm1);
        StrtNm1.setTextContent("str1234");
        
        Element BldgNb1 = doc.createElement("BldgNb");
        PstlAdr1_1.appendChild(BldgNb1);
        BldgNb1.setTextContent("str1234");
        
        Element PstCd1 = doc.createElement("PstCd");
        PstlAdr1_1.appendChild(PstCd1);
        PstCd1.setTextContent("str1234");
        
        Element TwnNm1 = doc.createElement("TwnNm");
        PstlAdr1_1.appendChild(TwnNm1);
        TwnNm1.setTextContent("str1234");
        
        Element CtrySubDvsn1 = doc.createElement("CtrySubDvsn");
        PstlAdr1_1.appendChild(CtrySubDvsn1);
        CtrySubDvsn1.setTextContent("str1234");
        
        
        Element Ctry1_1 = doc.createElement("Ctry");
        PstlAdr1_1.appendChild(Ctry1_1);
        Ctry1_1.setTextContent("NL");
        
        //Tag Id
        Element Id_new1 = doc.createElement("Id");
        UltmtCdtr1.appendChild(Id_new1);
        Element OrgId1 = doc.createElement("OrgId");
        Id_new1.appendChild(OrgId1);
        Element AnyBIC1 = doc.createElement("AnyBIC");
        OrgId1.appendChild(AnyBIC1);
        AnyBIC1.setTextContent("RABONL2U");
        
        //Tag InstgAgt
        
        Element InstgAgt1 = doc.createElement("InstgAgt");
        DrctDbtTxInf1.appendChild(InstgAgt1);
        Element FinInstnId1_New = doc.createElement("FinInstnId");
        InstgAgt1.appendChild(FinInstnId1_New);
        Element BICFI1_1 = doc.createElement("BICFI");
        FinInstnId1_New.appendChild(BICFI1_1);
        BICFI1_1.setTextContent("NNBANL2G");
        
        //Tag Dbtr
        Element Dbtr1_New = doc.createElement("Dbtr");
        DrctDbtTxInf1.appendChild(Dbtr1_New);
        Element Nm_1 = doc.createElement("Nm");
        Dbtr1_New.appendChild(Nm_1);
        Nm_1.setTextContent("D. Demir Halk Debtor");
        
        //Tag DbtrAcct
        
        Element DbtrAcct1 = doc.createElement("DbtrAcct");
        DrctDbtTxInf1.appendChild(DbtrAcct1);
        Element Id_1_New = doc.createElement("Id");
        DbtrAcct1.appendChild(Id_1_New);
        Element IBAN1 = doc.createElement("IBAN");
        Id_1_New.appendChild(IBAN1);
        IBAN1.setTextContent("NL61NNBA0719053412");
        
        //Tag DbtrAgt
        Element DbtrAgt1 = doc.createElement("DbtrAgt");
        DrctDbtTxInf1.appendChild(DbtrAgt1);
        Element FinInstnId1_1 = doc.createElement("FinInstnId");
        DbtrAgt1.appendChild(FinInstnId1_1);
        Element BICFI1_1_1 = doc.createElement("BICFI");
        FinInstnId1_1.appendChild(BICFI1_1_1);
        BICFI1_1_1.setTextContent("NNBANL2G");
        
        //Tag UltmtDbtr
        
        Element UltmtDbtr1 = doc.createElement("UltmtDbtr");
        DrctDbtTxInf1.appendChild(UltmtDbtr1);
        Element Nm1_1_1 = doc.createElement("Nm");
        UltmtDbtr1.appendChild(Nm1_1_1);
        Nm1_1_1.setTextContent("str1234");
        
        //Tag PstlAdr
        
        Element PstlAdr1_1_1 = doc.createElement("PstlAdr");
        UltmtDbtr1.appendChild(PstlAdr1_1_1);
        //PstlAdr1_1_1.setTextContent("str1338");
        
        Element Dept1_1 = doc.createElement("Dept");
        PstlAdr1_1_1.appendChild(Dept1_1);
        Dept1_1.setTextContent("str1234");
        
        Element SubDept1_1 = doc.createElement("SubDept");
        PstlAdr1_1_1.appendChild(SubDept1_1);
        SubDept1_1.setTextContent("str1234");
        
        Element StrtNm1_1 = doc.createElement("StrtNm");
        PstlAdr1_1_1.appendChild(StrtNm1_1);
        StrtNm1_1.setTextContent("str1234");
        
        Element BldgNb1_1 = doc.createElement("BldgNb");
        PstlAdr1_1_1.appendChild(BldgNb1_1);
        BldgNb1_1.setTextContent("str1234");
        
        Element PstCd1_1 = doc.createElement("PstCd");
        PstlAdr1_1_1.appendChild(PstCd1_1);
        PstCd1_1.setTextContent("str1234");
        
        Element TwnNm1_1 = doc.createElement("TwnNm");
        PstlAdr1_1_1.appendChild(TwnNm1_1);
        TwnNm1_1.setTextContent("str1234");
        
        Element CtrySubDvsn1_1 = doc.createElement("CtrySubDvsn");
        PstlAdr1_1_1.appendChild(CtrySubDvsn1_1);
        CtrySubDvsn1_1.setTextContent("str1234");
        
        Element Ctry11 = doc.createElement("Ctry");
        PstlAdr1_1_1.appendChild(Ctry11);
        Ctry11.setTextContent("NL");
        
        Element Id_Inside1 = doc.createElement("Id");
        UltmtDbtr1.appendChild(Id_Inside1);
        
        
        
        //Tag OrgId
        Element OrgId1_1 = doc.createElement("OrgId");
        Id_Inside1.appendChild(OrgId1_1);
        Element AnyBIC1_1 = doc.createElement("AnyBIC");
        OrgId1_1.appendChild(AnyBIC1_1);
        AnyBIC1_1.setTextContent("RABONL2U");
        
        //RmtInf
        Element RmtInf1 = doc.createElement("RmtInf");
        DrctDbtTxInf1.appendChild(RmtInf1);
        Element Ustrd1 = doc.createElement("Ustrd");
        RmtInf1.appendChild(Ustrd1);
        Ustrd1.setTextContent("Direct Debit Collection");
        
        
       //Amount2

        
        Element DrctDbtTxInf2 = doc.createElement("DrctDbtTxInf");
        FIToFICstmrDrctDbt.appendChild(DrctDbtTxInf2);
        Element PmtId2 = doc.createElement("PmtId");
        DrctDbtTxInf2.appendChild(PmtId2);
        Element InstrId2 = doc.createElement("InstrId");
        PmtId2.appendChild(InstrId2);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf2 = doc.createElement("PmtTpInf");
        DrctDbtTxInf2.appendChild(PmtTpInf2);
        Element SvcLvl2 = doc.createElement("SvcLvl");
        PmtTpInf2.appendChild(SvcLvl2);
        Element Cd2 = doc.createElement("Cd");
        SvcLvl2.appendChild(Cd2);
        Cd2.setTextContent("SEPA");
        
        //tag LclInstrm
        
        //Element PmtTpInf2 = doc.createElement("PmtTpInf");
        //DrctDbtTxInf2.appendChild(PmtTpInf2);
        Element LclInstrm2 = doc.createElement("LclInstrm");
        PmtTpInf2.appendChild(LclInstrm2);
        Element Cd2_ = doc.createElement("Cd");
        LclInstrm2.appendChild(Cd2_);
        Cd2_.setTextContent("CORE");
        Element SeqTp2 = doc.createElement("SeqTp");
        PmtTpInf2.appendChild(SeqTp2);
        SeqTp2.setTextContent("RCUR");
        
        //tag CtgyPurp
        Element CtgyPurp2 = doc.createElement("CtgyPurp");
        PmtTpInf2.appendChild(CtgyPurp2);
        Element Cd2_2 = doc.createElement("Cd");
        CtgyPurp2.appendChild(Cd2_2);
        Cd2_2.setTextContent("Str1");
        
        InstrId2.setTextContent(MsgIdText);
        Element EndToEndId2 = doc.createElement("EndToEndId");
        PmtId2.appendChild(EndToEndId2);
        //String EndToEndIdText = "E1E"+Messagetime111;
        EndToEndId2.setTextContent(EndToEndIdText);
        Element TxId2 = doc.createElement("TxId");
        PmtId2.appendChild(TxId2);
        TxId2.setTextContent("TXDIDI1A100811F"+Messagetime111);
        
        
        Element IntrBkSttlmAmt2 = doc.createElement("IntrBkSttlmAmt");
        DrctDbtTxInf2.appendChild(IntrBkSttlmAmt2);
        IntrBkSttlmAmt2.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt2.setTextContent("12");
        Element ChrgBr2 = doc.createElement("ChrgBr");
        DrctDbtTxInf2.appendChild(ChrgBr2);
        ChrgBr2.setTextContent("SLEV");
        
        //ReqdColltnDt
        Element ReqdColltnDt2 = doc.createElement("ReqdColltnDt");
        DrctDbtTxInf2.appendChild(ReqdColltnDt2);
        ReqdColltnDt2.setTextContent(formattedNextDay);
        
        
        Element DrctDbtTx2 = doc.createElement("DrctDbtTx");
        DrctDbtTxInf2.appendChild(DrctDbtTx2);
        Element MndtRltdInf2 = doc.createElement("MndtRltdInf");
        DrctDbtTx2.appendChild(MndtRltdInf2);
        Element MndtId2 = doc.createElement("MndtId");
        MndtRltdInf2.appendChild(MndtId2);
        MndtId2.setTextContent("82012");
        
        Element DtOfSgntr2 = doc.createElement("DtOfSgntr");
        MndtRltdInf2.appendChild(DtOfSgntr2);
        DtOfSgntr2.setTextContent(formattedNextDay);
        
        Element AmdmntInd2 = doc.createElement("AmdmntInd");
        MndtRltdInf2.appendChild(AmdmntInd2);
        AmdmntInd2.setTextContent("true");
        
        
        //AmdmntInfDtls
        Element AmdmntInfDtls2 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf2.appendChild(AmdmntInfDtls2);
        Element OrgnlCdtrSchmeId2 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls2.appendChild(OrgnlCdtrSchmeId2);
        Element Nm2 = doc.createElement("Nm");
        OrgnlCdtrSchmeId2.appendChild(Nm2);
        Nm2.setTextContent("Vodafone Co");
        
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
        Id2_2.setTextContent("NL28ZZZ120750020000");
        
       
        //SchmeNm
        Element SchmeNm2 = doc.createElement("SchmeNm");
        Othr2.appendChild(SchmeNm2);
        Element Prtry2 = doc.createElement("Prtry");
        SchmeNm2.appendChild(Prtry2);
        Prtry2.setTextContent("SEPA");
        
        
        //OrgnlDbtrAcct
        Element OrgnlDbtrAcct2 = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls2.appendChild(OrgnlDbtrAcct2);
        Element Id_2_2_2 = doc.createElement("Id");
        OrgnlDbtrAcct2.appendChild(Id_2_2_2);
        Element IBAN2__2 = doc.createElement("IBAN");
        Id_2_2_2.appendChild(IBAN2__2);
        IBAN2__2.setTextContent("NL82NNBA0729052812");
        
        //Tag ElctrncSgntr2
        Element ElctrncSgntr2 = doc.createElement("ElctrncSgntr");
        MndtRltdInf2.appendChild(ElctrncSgntr2);
        ElctrncSgntr2.setTextContent("DIANA");
        
        //Tag CdtrSchmeId
        Element CdtrSchmeId2 = doc.createElement("CdtrSchmeId");
        DrctDbtTx2.appendChild(CdtrSchmeId2);
        Element Id_New_2 = doc.createElement("Id");
        CdtrSchmeId2.appendChild(Id_New_2);
        Element PrvtId2_2 = doc.createElement("PrvtId");
        Id_New_2.appendChild(PrvtId2_2);
        Element Othr_2 = doc.createElement("Othr");
        PrvtId2_2.appendChild(Othr_2);
        Element Id_New_2_2 = doc.createElement("Id");
        Othr_2.appendChild(Id_New_2_2);
        Id_New_2_2.setTextContent("NL28ZZZ120750020000");
        
        //Tag SchmeNm
//        <SchmeNm>
//        <Prtry>SEPA</Prtry>
//        </SchmeNm>
        
        Element SchmeNm2_2 = doc.createElement("SchmeNm");
        Id_New_2_2.appendChild(SchmeNm2_2);
        Element Prtry2_2 = doc.createElement("Prtry");
        SchmeNm2_2.appendChild(Prtry2_2);
        Prtry2_2.setTextContent("SEPA");
        
        
        //Tag Cdtr
        Element Cdtr2 = doc.createElement("Cdtr");
        DrctDbtTxInf2.appendChild(Cdtr2);
        Element Nm2_New = doc.createElement("Nm");
        Cdtr2.appendChild(Nm2_New);
        Nm2_New.setTextContent("Vodafon Co");
        
        //Tag PstlAdr
        Element PstlAdr2 = doc.createElement("PstlAdr");
        Cdtr2.appendChild(PstlAdr2);
        Element Ctry2 = doc.createElement("Ctry");
        PstlAdr2.appendChild(Ctry2);
        Ctry2.setTextContent("NL");
        //<AdrLine>s Gravenhof 2 D</AdrLine>
        //<AdrLine>7201 DN ZUTPHEN</AdrLine>
        Element AdrLine2 = doc.createElement("AdrLine");
        PstlAdr2.appendChild(AdrLine2);
        AdrLine2.setTextContent("s Gravenhof 2 D");
        Element AdrLine2_2 = doc.createElement("AdrLine");
        PstlAdr2.appendChild(AdrLine2_2);
        AdrLine2_2.setTextContent("7201 DN ZUTPHEN");
        
        
        
        //Tag CdtrAcct
        Element CdtrAcct2 = doc.createElement("CdtrAcct");
        DrctDbtTxInf2.appendChild(CdtrAcct2);
        Element Id_2_Acct = doc.createElement("Id");
        CdtrAcct2.appendChild(Id_2_Acct);
        Element IBAN_2 = doc.createElement("IBAN");
        Id_2_Acct.appendChild(IBAN_2);
        IBAN_2.setTextContent("NL12RABO0200001212");
        
        //Tag CdtrAgt
        Element CdtrAgt2 = doc.createElement("CdtrAgt");
        DrctDbtTxInf2.appendChild(CdtrAgt2);
        Element FinInstnId2 = doc.createElement("FinInstnId");
        CdtrAgt2.appendChild(FinInstnId2);
        Element BICFI2_New = doc.createElement("BICFI");
        FinInstnId2.appendChild(BICFI2_New);
        BICFI2_New.setTextContent("RABONL2U");
        
        //Tag UltmtCdtr
        Element UltmtCdtr2 = doc.createElement("UltmtCdtr");
        DrctDbtTxInf2.appendChild(UltmtCdtr2);
        Element Nm2_2 = doc.createElement("Nm");
        UltmtCdtr2.appendChild(Nm2_2);
        Nm2_2.setTextContent("str1234");
        
        //Tag PstlAdr
        Element PstlAdr2_2 = doc.createElement("PstlAdr");
        UltmtCdtr2.appendChild(PstlAdr2_2);
        Element Dept2 = doc.createElement("Dept");
        PstlAdr2_2.appendChild(Dept2);
        Dept2.setTextContent("str1234");
        
        //Tag SubDept
        Element SubDept2 = doc.createElement("SubDept");
        PstlAdr2_2.appendChild(SubDept2);
        SubDept2.setTextContent("str1234");
        
        Element StrtNm2 = doc.createElement("StrtNm");
        PstlAdr2_2.appendChild(StrtNm2);
        StrtNm2.setTextContent("str1234");
        
        Element BldgNb2 = doc.createElement("BldgNb");
        PstlAdr2_2.appendChild(BldgNb2);
        BldgNb2.setTextContent("str1234");
        
        Element PstCd2 = doc.createElement("PstCd");
        PstlAdr2_2.appendChild(PstCd2);
        PstCd2.setTextContent("str1234");
        
        Element TwnNm2 = doc.createElement("TwnNm");
        PstlAdr2_2.appendChild(TwnNm2);
        TwnNm2.setTextContent("str1234");
        
        Element CtrySubDvsn2 = doc.createElement("CtrySubDvsn");
        PstlAdr2_2.appendChild(CtrySubDvsn2);
        CtrySubDvsn2.setTextContent("NL");
        
        
        Element Ctry2_2 = doc.createElement("Ctry");
        PstlAdr2_2.appendChild(Ctry2_2);
        Ctry2_2.setTextContent("NL");
        
        //Tag Id
        Element Id_new2 = doc.createElement("Id");
        UltmtCdtr2.appendChild(Id_new2);
        Element OrgId2 = doc.createElement("OrgId");
        Id_new2.appendChild(OrgId2);
        Element AnyBIC2 = doc.createElement("AnyBIC");
        OrgId2.appendChild(AnyBIC2);
        AnyBIC2.setTextContent("RABONL2U");
        
        //Tag InstgAgt
        
        Element InstgAgt2 = doc.createElement("InstgAgt");
        DrctDbtTxInf2.appendChild(InstgAgt2);
        Element FinInstnId2_New = doc.createElement("FinInstnId");
        InstgAgt2.appendChild(FinInstnId2_New);
        Element BICFI2_2 = doc.createElement("BICFI");
        FinInstnId2_New.appendChild(BICFI2_2);
        BICFI2_2.setTextContent("RABONL2U");
        
        //Tag Dbtr
        Element Dbtr2_New = doc.createElement("Dbtr");
        DrctDbtTxInf2.appendChild(Dbtr2_New);
        Element Nm_2 = doc.createElement("Nm");
        Dbtr2_New.appendChild(Nm_2);
        Nm_2.setTextContent("D. Demir Halk Debtor");
        
        //Tag DbtrAcct
        
        Element DbtrAcct2 = doc.createElement("DbtrAcct");
        DrctDbtTxInf2.appendChild(DbtrAcct2);
        Element Id_2_New = doc.createElement("Id");
        DbtrAcct2.appendChild(Id_2_New);
        Element IBAN2 = doc.createElement("IBAN");
        Id_2_New.appendChild(IBAN2);
        IBAN2.setTextContent("NL82NNBA0729052812");
        
        //Tag DbtrAgt
        Element DbtrAgt2 = doc.createElement("DbtrAgt");
        DrctDbtTxInf2.appendChild(DbtrAgt2);
        Element FinInstnId2_2 = doc.createElement("FinInstnId");
        DbtrAgt2.appendChild(FinInstnId2_2);
        Element BICFI2_2_2 = doc.createElement("BICFI");
        FinInstnId2_2.appendChild(BICFI2_2_2);
        BICFI2_2_2.setTextContent("NNBANL2G");
        
        //Tag UltmtDbtr
        
        Element UltmtDbtr2 = doc.createElement("UltmtDbtr");
        DrctDbtTxInf2.appendChild(UltmtDbtr2);
        Element Nm2_2_2 = doc.createElement("Nm");
        UltmtDbtr2.appendChild(Nm2_2_2);
        Nm2_2_2.setTextContent("str1234");
        
        //Tag PstlAdr
        
        Element PstlAdr2_2_2 = doc.createElement("PstlAdr");
        UltmtDbtr2.appendChild(PstlAdr2_2_2);
        //PstlAdr2_2_2.setTextContent("str1234");
        
        Element Dept2_2 = doc.createElement("Dept");
        PstlAdr2_2_2.appendChild(Dept2_2);
        Dept2_2.setTextContent("str1234");
        
        Element SubDept2_2 = doc.createElement("SubDept");
        PstlAdr2_2_2.appendChild(SubDept2_2);
        SubDept2_2.setTextContent("str1234");
        
        Element StrtNm2_2 = doc.createElement("StrtNm");
        PstlAdr2_2_2.appendChild(StrtNm2_2);
        StrtNm2_2.setTextContent("str1234");
        
        Element BldgNb2_2 = doc.createElement("BldgNb");
        PstlAdr2_2_2.appendChild(BldgNb2_2);
        BldgNb2_2.setTextContent("str1234");
        
        Element PstCd2_2 = doc.createElement("PstCd");
        PstlAdr2_2_2.appendChild(PstCd2_2);
        PstCd2_2.setTextContent("str1234");
        
        Element TwnNm2_2 = doc.createElement("TwnNm");
        PstlAdr2_2_2.appendChild(TwnNm2_2);
        TwnNm2_2.setTextContent("str1234");
        
        Element CtrySubDvsn2_2 = doc.createElement("CtrySubDvsn");
        PstlAdr2_2_2.appendChild(CtrySubDvsn2_2);
        CtrySubDvsn2_2.setTextContent("str1234");
        
        Element Ctry12 = doc.createElement("Ctry");
        PstlAdr2_2_2.appendChild(Ctry12);
        Ctry12.setTextContent("str1234");
        
        Element Id_Inside2 = doc.createElement("Id");
        UltmtDbtr2.appendChild(Id_Inside2);
        
        
        
        //Tag OrgId
        Element OrgId2_2 = doc.createElement("OrgId");
        Id_Inside2.appendChild(OrgId2_2);
        Element AnyBIC2_2 = doc.createElement("AnyBIC");
        OrgId2_2.appendChild(AnyBIC2_2);
        AnyBIC2_2.setTextContent("NNBANL2G");
        
        //RmtInf
        Element RmtInf2 = doc.createElement("RmtInf");
        DrctDbtTxInf2.appendChild(RmtInf2);
        Element Ustrd2 = doc.createElement("Ustrd");
        RmtInf2.appendChild(Ustrd2);
        Ustrd2.setTextContent("Direct Debit Collection");
        
        //Amount3

        
        Element DrctDbtTxInf3 = doc.createElement("DrctDbtTxInf");
        FIToFICstmrDrctDbt.appendChild(DrctDbtTxInf3);
        Element PmtId3 = doc.createElement("PmtId");
        DrctDbtTxInf3.appendChild(PmtId3);
        Element InstrId3 = doc.createElement("InstrId");
        PmtId3.appendChild(InstrId3);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf3 = doc.createElement("PmtTpInf");
        DrctDbtTxInf3.appendChild(PmtTpInf3);
        Element SvcLvl3 = doc.createElement("SvcLvl");
        PmtTpInf3.appendChild(SvcLvl3);
        Element Cd3 = doc.createElement("Cd");
        SvcLvl3.appendChild(Cd3);
        Cd3.setTextContent("SEPA");
        
        //tag LclInstrm
        
        //Element PmtTpInf3 = doc.createElement("PmtTpInf");
        //DrctDbtTxInf3.appendChild(PmtTpInf3);
        Element LclInstrm3 = doc.createElement("LclInstrm");
        PmtTpInf3.appendChild(LclInstrm3);
        Element Cd3_ = doc.createElement("Cd");
        LclInstrm3.appendChild(Cd3_);
        Cd3_.setTextContent("CORE");
        Element SeqTp3 = doc.createElement("SeqTp");
        PmtTpInf3.appendChild(SeqTp3);
        SeqTp3.setTextContent("RCUR");
        
        //tag CtgyPurp
        Element CtgyPurp3 = doc.createElement("CtgyPurp");
        PmtTpInf3.appendChild(CtgyPurp3);
        Element Cd3_3 = doc.createElement("Cd");
        CtgyPurp3.appendChild(Cd3_3);
        Cd3_3.setTextContent("Str1");
        
        InstrId3.setTextContent(MsgIdText);
        Element EndToEndId3 = doc.createElement("EndToEndId");
        PmtId3.appendChild(EndToEndId3);
        //String EndToEndIdText = "E1E"+Messagetime111;
        EndToEndId3.setTextContent(EndToEndIdText);
        Element TxId3 = doc.createElement("TxId");
        PmtId3.appendChild(TxId3);
        TxId3.setTextContent("TXDIDI1A100811F"+Messagetime111);
        
        
        Element IntrBkSttlmAmt3 = doc.createElement("IntrBkSttlmAmt");
        DrctDbtTxInf3.appendChild(IntrBkSttlmAmt3);
        IntrBkSttlmAmt3.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt3.setTextContent("13");
        Element ChrgBr3 = doc.createElement("ChrgBr");
        DrctDbtTxInf3.appendChild(ChrgBr3);
        ChrgBr3.setTextContent("SLEV");
        
        //ReqdColltnDt
        Element ReqdColltnDt3 = doc.createElement("ReqdColltnDt");
        DrctDbtTxInf3.appendChild(ReqdColltnDt3);
        ReqdColltnDt3.setTextContent(formattedNextDay);
        
        
        Element DrctDbtTx3 = doc.createElement("DrctDbtTx");
        DrctDbtTxInf3.appendChild(DrctDbtTx3);
        Element MndtRltdInf3 = doc.createElement("MndtRltdInf");
        DrctDbtTx3.appendChild(MndtRltdInf3);
        Element MndtId3 = doc.createElement("MndtId");
        MndtRltdInf3.appendChild(MndtId3);
        MndtId3.setTextContent("83033");
        
        Element DtOfSgntr3 = doc.createElement("DtOfSgntr");
        MndtRltdInf3.appendChild(DtOfSgntr3);
        DtOfSgntr3.setTextContent(formattedNextDay);
        
        Element AmdmntInd3 = doc.createElement("AmdmntInd");
        MndtRltdInf3.appendChild(AmdmntInd3);
        AmdmntInd3.setTextContent("true");
        
        
        //AmdmntInfDtls
        Element AmdmntInfDtls3 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf3.appendChild(AmdmntInfDtls3);
        Element OrgnlCdtrSchmeId3 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls3.appendChild(OrgnlCdtrSchmeId3);
        Element Nm3 = doc.createElement("Nm");
        OrgnlCdtrSchmeId3.appendChild(Nm3);
        Nm3.setTextContent("Vodafone Co");
        
        Element Id3 = doc.createElement("Id");
        OrgnlCdtrSchmeId3.appendChild(Id3);
        //PrvtId
        
        Element PrvtId3 = doc.createElement("PrvtId");
        Id3.appendChild(PrvtId3);
        //Othr
        Element Othr3 = doc.createElement("Othr");
        PrvtId3.appendChild(Othr3);
        Element Id3_3 = doc.createElement("Id");
        Othr3.appendChild(Id3_3);
        Id3_3.setTextContent("NL38ZZZ330750030000");
        
       
        //SchmeNm
        Element SchmeNm3 = doc.createElement("SchmeNm");
        Othr3.appendChild(SchmeNm3);
        Element Prtry3 = doc.createElement("Prtry");
        SchmeNm3.appendChild(Prtry3);
        Prtry3.setTextContent("SEPA");
        
        
        //OrgnlDbtrAcct
        Element OrgnlDbtrAcct3 = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls3.appendChild(OrgnlDbtrAcct3);
        Element Id_3_3_3 = doc.createElement("Id");
        OrgnlDbtrAcct3.appendChild(Id_3_3_3);
        Element IBAN3__3 = doc.createElement("IBAN");
        Id_3_3_3.appendChild(IBAN3__3);
        IBAN3__3.setTextContent("NL83NNBA0739053833");
        
        //Tag ElctrncSgntr3
        Element ElctrncSgntr3 = doc.createElement("ElctrncSgntr");
        MndtRltdInf3.appendChild(ElctrncSgntr3);
        ElctrncSgntr3.setTextContent("DIANA");
        
        //Tag CdtrSchmeId
        Element CdtrSchmeId3 = doc.createElement("CdtrSchmeId");
        DrctDbtTx3.appendChild(CdtrSchmeId3);
        Element Id_New_3 = doc.createElement("Id");
        CdtrSchmeId3.appendChild(Id_New_3);
        Element PrvtId3_3 = doc.createElement("PrvtId");
        Id_New_3.appendChild(PrvtId3_3);
        Element Othr_3 = doc.createElement("Othr");
        PrvtId3_3.appendChild(Othr_3);
        Element Id_New_3_3 = doc.createElement("Id");
        Othr_3.appendChild(Id_New_3_3);
        Id_New_3_3.setTextContent("NL38ZZZ330750030000");
        
        //Tag SchmeNm
//        <SchmeNm>
//        <Prtry>SEPA</Prtry>
//        </SchmeNm>
        
        Element SchmeNm3_3 = doc.createElement("SchmeNm");
        Othr_3.appendChild(SchmeNm3_3);
        Element Prtry3_3 = doc.createElement("Prtry");
        SchmeNm3_3.appendChild(Prtry3_3);
        Prtry3_3.setTextContent("SEPA");
        
        
        //Tag Cdtr
        Element Cdtr3 = doc.createElement("Cdtr");
        DrctDbtTxInf3.appendChild(Cdtr3);
        Element Nm3_New = doc.createElement("Nm");
        Cdtr3.appendChild(Nm3_New);
        Nm3_New.setTextContent("Vodafon Co");
        
        //Tag PstlAdr
        Element PstlAdr3 = doc.createElement("PstlAdr");
        Cdtr3.appendChild(PstlAdr3);
        Element Ctry3 = doc.createElement("Ctry");
        PstlAdr3.appendChild(Ctry3);
        Ctry3.setTextContent("NL");
        //<AdrLine>s Gravenhof 2 D</AdrLine>
        //<AdrLine>7303 DN ZUTPHEN</AdrLine>
        Element AdrLine3 = doc.createElement("AdrLine");
        PstlAdr3.appendChild(AdrLine3);
        AdrLine3.setTextContent("s Gravenhof 2 D");
        Element AdrLine3_3 = doc.createElement("AdrLine");
        PstlAdr3.appendChild(AdrLine3_3);
        AdrLine3_3.setTextContent("7303 DN ZUTPHEN");
        
        
        
        //Tag CdtrAcct
        Element CdtrAcct3 = doc.createElement("CdtrAcct");
        DrctDbtTxInf3.appendChild(CdtrAcct3);
        Element Id_3_Acct = doc.createElement("Id");
        CdtrAcct3.appendChild(Id_3_Acct);
        Element IBAN_3 = doc.createElement("IBAN");
        Id_3_Acct.appendChild(IBAN_3);
        IBAN_3.setTextContent("NL33RABO0300003333");
        
        //Tag CdtrAgt
        Element CdtrAgt3 = doc.createElement("CdtrAgt");
        DrctDbtTxInf3.appendChild(CdtrAgt3);
        Element FinInstnId3 = doc.createElement("FinInstnId");
        CdtrAgt3.appendChild(FinInstnId3);
        Element BICFI3_New = doc.createElement("BICFI");
        FinInstnId3.appendChild(BICFI3_New);
        BICFI3_New.setTextContent("DEUTESBB");
        
        //Tag UltmtCdtr
        Element UltmtCdtr3 = doc.createElement("UltmtCdtr");
        DrctDbtTxInf3.appendChild(UltmtCdtr3);
        Element Nm3_3 = doc.createElement("Nm");
        UltmtCdtr3.appendChild(Nm3_3);
        Nm3_3.setTextContent("str1234");
        
        //Tag PstlAdr
        Element PstlAdr3_3 = doc.createElement("PstlAdr");
        UltmtCdtr3.appendChild(PstlAdr3_3);
        Element Dept3 = doc.createElement("Dept");
        PstlAdr3_3.appendChild(Dept3);
        Dept3.setTextContent("str1234");
        
        //Tag SubDept
        Element SubDept3 = doc.createElement("SubDept");
        PstlAdr3_3.appendChild(SubDept3);
        SubDept3.setTextContent("str1234");
        
        Element StrtNm3 = doc.createElement("StrtNm");
        PstlAdr3_3.appendChild(StrtNm3);
        StrtNm3.setTextContent("str1234");
        
        Element BldgNb3 = doc.createElement("BldgNb");
        PstlAdr3_3.appendChild(BldgNb3);
        BldgNb3.setTextContent("str1234");
        
        Element PstCd3 = doc.createElement("PstCd");
        PstlAdr3_3.appendChild(PstCd3);
        PstCd3.setTextContent("str1234");
        
        Element TwnNm3 = doc.createElement("TwnNm");
        PstlAdr3_3.appendChild(TwnNm3);
        TwnNm3.setTextContent("str1234");
        
        Element CtrySubDvsn3 = doc.createElement("CtrySubDvsn");
        PstlAdr3_3.appendChild(CtrySubDvsn3);
        CtrySubDvsn3.setTextContent("str1234");
        
        
        Element Ctry3_3 = doc.createElement("Ctry");
        PstlAdr3_3.appendChild(Ctry3_3);
        Ctry3_3.setTextContent("NL");
        
        //Tag Id
        Element Id_new3 = doc.createElement("Id");
        UltmtCdtr3.appendChild(Id_new3);
        Element OrgId3 = doc.createElement("OrgId");
        Id_new3.appendChild(OrgId3);
        Element AnyBIC3 = doc.createElement("AnyBIC");
        OrgId3.appendChild(AnyBIC3);
        AnyBIC3.setTextContent("RABONL2U");
        
        //Tag InstgAgt
        
        Element InstgAgt3 = doc.createElement("InstgAgt");
        DrctDbtTxInf3.appendChild(InstgAgt3);
        Element FinInstnId3_New = doc.createElement("FinInstnId");
        InstgAgt3.appendChild(FinInstnId3_New);
        Element BICFI3_3 = doc.createElement("BICFI");
        FinInstnId3_New.appendChild(BICFI3_3);
        BICFI3_3.setTextContent("RABONL2U");
        
        //Tag Dbtr
        Element Dbtr3_New = doc.createElement("Dbtr");
        DrctDbtTxInf3.appendChild(Dbtr3_New);
        Element Nm_3 = doc.createElement("Nm");
        Dbtr3_New.appendChild(Nm_3);
        Nm_3.setTextContent("D. Demir Halk Debtor");
        
        //Tag DbtrAcct
        
        Element DbtrAcct3 = doc.createElement("DbtrAcct");
        DrctDbtTxInf3.appendChild(DbtrAcct3);
        Element Id_3_New = doc.createElement("Id");
        DbtrAcct3.appendChild(Id_3_New);
        Element IBAN3 = doc.createElement("IBAN");
        Id_3_New.appendChild(IBAN3);
        IBAN3.setTextContent("NL83NNBA0739053833");
        
        //Tag DbtrAgt
        Element DbtrAgt3 = doc.createElement("DbtrAgt");
        DrctDbtTxInf3.appendChild(DbtrAgt3);
        Element FinInstnId3_3 = doc.createElement("FinInstnId");
        DbtrAgt3.appendChild(FinInstnId3_3);
        Element BICFI3_3_3 = doc.createElement("BICFI");
        FinInstnId3_3.appendChild(BICFI3_3_3);
        BICFI3_3_3.setTextContent("RABONL2U");
        
        //Tag UltmtDbtr
        
        Element UltmtDbtr3 = doc.createElement("UltmtDbtr");
        DrctDbtTxInf3.appendChild(UltmtDbtr3);
        Element Nm3_3_3 = doc.createElement("Nm");
        UltmtDbtr3.appendChild(Nm3_3_3);
        Nm3_3_3.setTextContent("str1234");
        
        //Tag PstlAdr
        
        Element PstlAdr3_3_3 = doc.createElement("PstlAdr");
        UltmtDbtr3.appendChild(PstlAdr3_3_3);
        //PstlAdr3_3_3.setTextContent("str1234");
        
        Element Dept3_3 = doc.createElement("Dept");
        PstlAdr3_3_3.appendChild(Dept3_3);
        Dept3_3.setTextContent("str1234");
        
        Element SubDept3_3 = doc.createElement("SubDept");
        PstlAdr3_3_3.appendChild(SubDept3_3);
        SubDept3_3.setTextContent("str1234");
        
        Element StrtNm3_3 = doc.createElement("StrtNm");
        PstlAdr3_3_3.appendChild(StrtNm3_3);
        StrtNm3_3.setTextContent("str1234");
        
        Element BldgNb3_3 = doc.createElement("BldgNb");
        PstlAdr3_3_3.appendChild(BldgNb3_3);
        BldgNb3_3.setTextContent("str1234");
        
        Element PstCd3_3 = doc.createElement("PstCd");
        PstlAdr3_3_3.appendChild(PstCd3_3);
        PstCd3_3.setTextContent("str1234");
        
        Element TwnNm3_3 = doc.createElement("TwnNm");
        PstlAdr3_3_3.appendChild(TwnNm3_3);
        TwnNm3_3.setTextContent("str1234");
        
        Element CtrySubDvsn3_3 = doc.createElement("CtrySubDvsn");
        PstlAdr3_3_3.appendChild(CtrySubDvsn3_3);
        CtrySubDvsn3_3.setTextContent("str1234");
        
        Element Ctry33 = doc.createElement("Ctry");
        PstlAdr3_3_3.appendChild(Ctry33);
        Ctry33.setTextContent("str1234");
        
        Element Id_Inside3 = doc.createElement("Id");
        UltmtDbtr3.appendChild(Id_Inside3);
        
        
        
        //Tag OrgId
        Element OrgId3_3 = doc.createElement("OrgId");
        Id_Inside3.appendChild(OrgId3_3);
        Element AnyBIC3_3 = doc.createElement("AnyBIC");
        OrgId3_3.appendChild(AnyBIC3_3);
        AnyBIC3_3.setTextContent("RABONL2U");
        
        //RmtInf
        Element RmtInf3 = doc.createElement("RmtInf");
        DrctDbtTxInf3.appendChild(RmtInf3);
        Element Ustrd3 = doc.createElement("Ustrd");
        RmtInf3.appendChild(Ustrd3);
        Ustrd3.setTextContent("Direct Debit Collection");
        
        

        
        Element DrctDbtTxInf4 = doc.createElement("DrctDbtTxInf");
        FIToFICstmrDrctDbt.appendChild(DrctDbtTxInf4);
        Element PmtId4 = doc.createElement("PmtId");
        DrctDbtTxInf4.appendChild(PmtId4);
        Element InstrId4 = doc.createElement("InstrId");
        PmtId4.appendChild(InstrId4);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf4 = doc.createElement("PmtTpInf");
        DrctDbtTxInf4.appendChild(PmtTpInf4);
        Element SvcLvl4 = doc.createElement("SvcLvl");
        PmtTpInf4.appendChild(SvcLvl4);
        Element Cd4 = doc.createElement("Cd");
        SvcLvl4.appendChild(Cd4);
        Cd4.setTextContent("SEPA");
        
        //tag LclInstrm
        
        //Element PmtTpInf4 = doc.createElement("PmtTpInf");
        //DrctDbtTxInf4.appendChild(PmtTpInf4);
        Element LclInstrm4 = doc.createElement("LclInstrm");
        PmtTpInf4.appendChild(LclInstrm4);
        Element Cd4_ = doc.createElement("Cd");
        LclInstrm4.appendChild(Cd4_);
        Cd4_.setTextContent("CORE");
        Element SeqTp4 = doc.createElement("SeqTp");
        PmtTpInf4.appendChild(SeqTp4);
        SeqTp4.setTextContent("RCUR");
        
        //tag CtgyPurp
        Element CtgyPurp4 = doc.createElement("CtgyPurp");
        PmtTpInf4.appendChild(CtgyPurp4);
        Element Cd4_4 = doc.createElement("Cd");
        CtgyPurp4.appendChild(Cd4_4);
        Cd4_4.setTextContent("Str1");
        
        InstrId4.setTextContent(MsgIdText);
        Element EndToEndId4 = doc.createElement("EndToEndId");
        PmtId4.appendChild(EndToEndId4);
        //String EndToEndIdText = "E1E"+Messagetime111;
        EndToEndId4.setTextContent(EndToEndIdText);
        Element TxId4 = doc.createElement("TxId");
        PmtId4.appendChild(TxId4);
        TxId4.setTextContent("TXDIDI1A100811F"+Messagetime111);
        
        
        Element IntrBkSttlmAmt4 = doc.createElement("IntrBkSttlmAmt");
        DrctDbtTxInf4.appendChild(IntrBkSttlmAmt4);
        IntrBkSttlmAmt4.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt4.setTextContent("14");
        Element ChrgBr4 = doc.createElement("ChrgBr");
        DrctDbtTxInf4.appendChild(ChrgBr4);
        ChrgBr4.setTextContent("SLEV");
        
        //ReqdColltnDt
        Element ReqdColltnDt4 = doc.createElement("ReqdColltnDt");
        DrctDbtTxInf4.appendChild(ReqdColltnDt4);
        ReqdColltnDt4.setTextContent(formattedNextDay);
        
        
        Element DrctDbtTx4 = doc.createElement("DrctDbtTx");
        DrctDbtTxInf4.appendChild(DrctDbtTx4);
        Element MndtRltdInf4 = doc.createElement("MndtRltdInf");
        DrctDbtTx4.appendChild(MndtRltdInf4);
        Element MndtId4 = doc.createElement("MndtId");
        MndtRltdInf4.appendChild(MndtId4);
        MndtId4.setTextContent("84044");
        
        Element DtOfSgntr4 = doc.createElement("DtOfSgntr");
        MndtRltdInf4.appendChild(DtOfSgntr4);
        DtOfSgntr4.setTextContent(formattedNextDay);
        
        Element AmdmntInd4 = doc.createElement("AmdmntInd");
        MndtRltdInf4.appendChild(AmdmntInd4);
        AmdmntInd4.setTextContent("true");
        
        
        //AmdmntInfDtls
        Element AmdmntInfDtls4 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf4.appendChild(AmdmntInfDtls4);
        Element OrgnlCdtrSchmeId4 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls4.appendChild(OrgnlCdtrSchmeId4);
        Element Nm4 = doc.createElement("Nm");
        OrgnlCdtrSchmeId4.appendChild(Nm4);
        Nm4.setTextContent("Vodafone Co");
        
        Element Id4 = doc.createElement("Id");
        OrgnlCdtrSchmeId4.appendChild(Id4);
        //PrvtId
        
        Element PrvtId4 = doc.createElement("PrvtId");
        Id4.appendChild(PrvtId4);
        //Othr
        Element Othr4 = doc.createElement("Othr");
        PrvtId4.appendChild(Othr4);
        Element Id4_4 = doc.createElement("Id");
        Othr4.appendChild(Id4_4);
        Id4_4.setTextContent("NL48ZZZ440750040000");
        
       
        //SchmeNm
        Element SchmeNm4 = doc.createElement("SchmeNm");
        Othr4.appendChild(SchmeNm4);
        Element Prtry4 = doc.createElement("Prtry");
        SchmeNm4.appendChild(Prtry4);
        Prtry4.setTextContent("SEPA");
        
        
        //OrgnlDbtrAcct
        Element OrgnlDbtrAcct4 = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls4.appendChild(OrgnlDbtrAcct4);
        Element Id_4_4_4 = doc.createElement("Id");
        OrgnlDbtrAcct4.appendChild(Id_4_4_4);
        Element IBAN4__4 = doc.createElement("IBAN");
        Id_4_4_4.appendChild(IBAN4__4);
        IBAN4__4.setTextContent("NL84NNBA0749054844");
        
        //Tag ElctrncSgntr4
        Element ElctrncSgntr4 = doc.createElement("ElctrncSgntr");
        MndtRltdInf4.appendChild(ElctrncSgntr4);
        ElctrncSgntr4.setTextContent("DIANA");
        
        //Tag CdtrSchmeId
        Element CdtrSchmeId4 = doc.createElement("CdtrSchmeId");
        DrctDbtTx4.appendChild(CdtrSchmeId4);
        Element Id_New_4 = doc.createElement("Id");
        CdtrSchmeId4.appendChild(Id_New_4);
        Element PrvtId4_4 = doc.createElement("PrvtId");
        Id_New_4.appendChild(PrvtId4_4);
        Element Othr_4 = doc.createElement("Othr");
        PrvtId4_4.appendChild(Othr_4);
        Element Id_New_4_4 = doc.createElement("Id");
        Othr_4.appendChild(Id_New_4_4);
        Id_New_4_4.setTextContent("NL48ZZZ440750040000");
        
        //Tag SchmeNm
//        <SchmeNm>
//        <Prtry>SEPA</Prtry>
//        </SchmeNm>
        
        Element SchmeNm4_4 = doc.createElement("SchmeNm");
        Othr_4.appendChild(SchmeNm4_4);
        Element Prtry4_4 = doc.createElement("Prtry");
        SchmeNm4_4.appendChild(Prtry4_4);
        Prtry4_4.setTextContent("SEPA");
        
        
        //Tag Cdtr
        Element Cdtr4 = doc.createElement("Cdtr");
        DrctDbtTxInf4.appendChild(Cdtr4);
        Element Nm4_New = doc.createElement("Nm");
        Cdtr4.appendChild(Nm4_New);
        Nm4_New.setTextContent("Vodafon Co");
        
        //Tag PstlAdr
        Element PstlAdr4 = doc.createElement("PstlAdr");
        Cdtr4.appendChild(PstlAdr4);
        Element Ctry4 = doc.createElement("Ctry");
        PstlAdr4.appendChild(Ctry4);
        Ctry4.setTextContent("NL");
        //<AdrLine>s Gravenhof 2 D</AdrLine>
        //<AdrLine>7404 DN ZUTPHEN</AdrLine>
        Element AdrLine4 = doc.createElement("AdrLine");
        PstlAdr4.appendChild(AdrLine4);
        AdrLine4.setTextContent("s Gravenhof 2 D");
        Element AdrLine4_4 = doc.createElement("AdrLine");
        PstlAdr4.appendChild(AdrLine4_4);
        AdrLine4_4.setTextContent("7404 DN ZUTPHEN");
        
        
        
        //Tag CdtrAcct
        Element CdtrAcct4 = doc.createElement("CdtrAcct");
        DrctDbtTxInf4.appendChild(CdtrAcct4);
        Element Id_4_Acct = doc.createElement("Id");
        CdtrAcct4.appendChild(Id_4_Acct);
        Element IBAN_4 = doc.createElement("IBAN");
        Id_4_Acct.appendChild(IBAN_4);
        IBAN_4.setTextContent("NL44RABO0400004444");
        
        //Tag CdtrAgt
        Element CdtrAgt4 = doc.createElement("CdtrAgt");
        DrctDbtTxInf4.appendChild(CdtrAgt4);
        Element FinInstnId4 = doc.createElement("FinInstnId");
        CdtrAgt4.appendChild(FinInstnId4);
        Element BICFI4_New = doc.createElement("BICFI");
        FinInstnId4.appendChild(BICFI4_New);
        BICFI4_New.setTextContent("RABONL2U");
        
        //Tag UltmtCdtr
        Element UltmtCdtr4 = doc.createElement("UltmtCdtr");
        DrctDbtTxInf4.appendChild(UltmtCdtr4);
        Element Nm4_4 = doc.createElement("Nm");
        UltmtCdtr4.appendChild(Nm4_4);
        Nm4_4.setTextContent("str1234");
        
        //Tag PstlAdr
        Element PstlAdr4_4 = doc.createElement("PstlAdr");
        UltmtCdtr4.appendChild(PstlAdr4_4);
        Element Dept4 = doc.createElement("Dept");
        PstlAdr4_4.appendChild(Dept4);
        Dept4.setTextContent("str1234");
        
        //Tag SubDept
        Element SubDept4 = doc.createElement("SubDept");
        PstlAdr4_4.appendChild(SubDept4);
        SubDept4.setTextContent("str1234");
        
        Element StrtNm4 = doc.createElement("StrtNm");
        PstlAdr4_4.appendChild(StrtNm4);
        StrtNm4.setTextContent("str1234");
        
        Element BldgNb4 = doc.createElement("BldgNb");
        PstlAdr4_4.appendChild(BldgNb4);
        BldgNb4.setTextContent("str1234");
        
        Element PstCd4 = doc.createElement("PstCd");
        PstlAdr4_4.appendChild(PstCd4);
        PstCd4.setTextContent("str1234");
        
        Element TwnNm4 = doc.createElement("TwnNm");
        PstlAdr4_4.appendChild(TwnNm4);
        TwnNm4.setTextContent("str1234");
        
        Element CtrySubDvsn4 = doc.createElement("CtrySubDvsn");
        PstlAdr4_4.appendChild(CtrySubDvsn4);
        CtrySubDvsn4.setTextContent("str1234");
        
        
        Element Ctry4_4 = doc.createElement("Ctry");
        PstlAdr4_4.appendChild(Ctry4_4);
        Ctry4_4.setTextContent("NL");
        
        //Tag Id
        Element Id_new4 = doc.createElement("Id");
        UltmtCdtr4.appendChild(Id_new4);
        Element OrgId4 = doc.createElement("OrgId");
        Id_new4.appendChild(OrgId4);
        Element AnyBIC4 = doc.createElement("AnyBIC");
        OrgId4.appendChild(AnyBIC4);
        AnyBIC4.setTextContent("RABONL2U");
        
        //Tag InstgAgt
        
        Element InstgAgt4 = doc.createElement("InstgAgt");
        DrctDbtTxInf4.appendChild(InstgAgt4);
        Element FinInstnId4_New = doc.createElement("FinInstnId");
        InstgAgt4.appendChild(FinInstnId4_New);
        Element BICFI4_4 = doc.createElement("BICFI");
        FinInstnId4_New.appendChild(BICFI4_4);
        BICFI4_4.setTextContent("RABONL2U");
        
        //Tag Dbtr
        Element Dbtr4_New = doc.createElement("Dbtr");
        DrctDbtTxInf4.appendChild(Dbtr4_New);
        Element Nm_4 = doc.createElement("Nm");
        Dbtr4_New.appendChild(Nm_4);
        Nm_4.setTextContent("D. Demir Halk Debtor");
        
        //Tag DbtrAcct
        
        Element DbtrAcct4 = doc.createElement("DbtrAcct");
        DrctDbtTxInf4.appendChild(DbtrAcct4);
        Element Id_4_New = doc.createElement("Id");
        DbtrAcct4.appendChild(Id_4_New);
        Element IBAN4 = doc.createElement("IBAN");
        Id_4_New.appendChild(IBAN4);
        IBAN4.setTextContent("NL84NNBA0749054844");
        
        //Tag DbtrAgt
        Element DbtrAgt4 = doc.createElement("DbtrAgt");
        DrctDbtTxInf4.appendChild(DbtrAgt4);
        Element FinInstnId4_4 = doc.createElement("FinInstnId");
        DbtrAgt4.appendChild(FinInstnId4_4);
        Element BICFI4_4_4 = doc.createElement("BICFI");
        FinInstnId4_4.appendChild(BICFI4_4_4);
        BICFI4_4_4.setTextContent("NNBANL4G");
        
        //Tag UltmtDbtr
        
        Element UltmtDbtr4 = doc.createElement("UltmtDbtr");
        DrctDbtTxInf4.appendChild(UltmtDbtr4);
        Element Nm4_4_4 = doc.createElement("Nm");
        UltmtDbtr4.appendChild(Nm4_4_4);
        Nm4_4_4.setTextContent("str1234");
        
        //Tag PstlAdr
        
        Element PstlAdr4_4_4 = doc.createElement("PstlAdr");
        UltmtDbtr4.appendChild(PstlAdr4_4_4);
        //PstlAdr4_4_4.setTextContent("str1234");
        
        Element Dept4_4 = doc.createElement("Dept");
        PstlAdr4_4_4.appendChild(Dept4_4);
        Dept4_4.setTextContent("str1234");
        
        Element SubDept4_4 = doc.createElement("SubDept");
        PstlAdr4_4_4.appendChild(SubDept4_4);
        SubDept4_4.setTextContent("str1234");
        
        Element StrtNm4_4 = doc.createElement("StrtNm");
        PstlAdr4_4_4.appendChild(StrtNm4_4);
        StrtNm4_4.setTextContent("str1234");
        
        Element BldgNb4_4 = doc.createElement("BldgNb");
        PstlAdr4_4_4.appendChild(BldgNb4_4);
        BldgNb4_4.setTextContent("str1234");
        
        Element PstCd4_4 = doc.createElement("PstCd");
        PstlAdr4_4_4.appendChild(PstCd4_4);
        PstCd4_4.setTextContent("str1234");
        
        Element TwnNm4_4 = doc.createElement("TwnNm");
        PstlAdr4_4_4.appendChild(TwnNm4_4);
        TwnNm4_4.setTextContent("str1234");
        
        Element CtrySubDvsn4_4 = doc.createElement("CtrySubDvsn");
        PstlAdr4_4_4.appendChild(CtrySubDvsn4_4);
        CtrySubDvsn4_4.setTextContent("str1234");
        
        Element Ctry44 = doc.createElement("Ctry");
        PstlAdr4_4_4.appendChild(Ctry44);
        Ctry44.setTextContent("str1234");
        
        Element Id_Inside4 = doc.createElement("Id");
        UltmtDbtr4.appendChild(Id_Inside4);
        
        
        
        //Tag OrgId
        Element OrgId4_4 = doc.createElement("OrgId");
        Id_Inside4.appendChild(OrgId4_4);
        Element AnyBIC4_4 = doc.createElement("AnyBIC");
        OrgId4_4.appendChild(AnyBIC4_4);
        AnyBIC4_4.setTextContent("RABONL2U");
        
        //RmtInf
        Element RmtInf4 = doc.createElement("RmtInf");
        DrctDbtTxInf4.appendChild(RmtInf4);
        Element Ustrd4 = doc.createElement("Ustrd");
        RmtInf4.appendChild(Ustrd4);
        Ustrd4.setTextContent("Direct Debit Collection");
        
        //Amount5

        
        Element DrctDbtTxInf5 = doc.createElement("DrctDbtTxInf");
        FIToFICstmrDrctDbt.appendChild(DrctDbtTxInf5);
        Element PmtId5 = doc.createElement("PmtId");
        DrctDbtTxInf5.appendChild(PmtId5);
        Element InstrId5 = doc.createElement("InstrId");
        PmtId5.appendChild(InstrId5);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf5 = doc.createElement("PmtTpInf");
        DrctDbtTxInf5.appendChild(PmtTpInf5);
        Element SvcLvl5 = doc.createElement("SvcLvl");
        PmtTpInf5.appendChild(SvcLvl5);
        Element Cd5 = doc.createElement("Cd");
        SvcLvl5.appendChild(Cd5);
        Cd5.setTextContent("SEPA");
        
        //tag LclInstrm
        
        //Element PmtTpInf5 = doc.createElement("PmtTpInf");
        //DrctDbtTxInf5.appendChild(PmtTpInf5);
        Element LclInstrm5 = doc.createElement("LclInstrm");
        PmtTpInf5.appendChild(LclInstrm5);
        Element Cd5_ = doc.createElement("Cd");
        LclInstrm5.appendChild(Cd5_);
        Cd5_.setTextContent("CORE");
        Element SeqTp5 = doc.createElement("SeqTp");
        PmtTpInf5.appendChild(SeqTp5);
        SeqTp5.setTextContent("RCUR");
        
        //tag CtgyPurp
        Element CtgyPurp5 = doc.createElement("CtgyPurp");
        PmtTpInf5.appendChild(CtgyPurp5);
        Element Cd5_5 = doc.createElement("Cd");
        CtgyPurp5.appendChild(Cd5_5);
        Cd5_5.setTextContent("Str1");
        
        InstrId5.setTextContent(MsgIdText);
        Element EndToEndId5 = doc.createElement("EndToEndId");
        PmtId5.appendChild(EndToEndId5);
        //String EndToEndIdText = "E1E"+Messagetime111;
        EndToEndId5.setTextContent(EndToEndIdText);
        Element TxId5 = doc.createElement("TxId");
        PmtId5.appendChild(TxId5);
        TxId5.setTextContent("TXDIDI1A100811F"+Messagetime111);
        
        
        Element IntrBkSttlmAmt5 = doc.createElement("IntrBkSttlmAmt");
        DrctDbtTxInf5.appendChild(IntrBkSttlmAmt5);
        IntrBkSttlmAmt5.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt5.setTextContent("15");
        Element ChrgBr5 = doc.createElement("ChrgBr");
        DrctDbtTxInf5.appendChild(ChrgBr5);
        ChrgBr5.setTextContent("SLEV");
        
        //ReqdColltnDt
        Element ReqdColltnDt5 = doc.createElement("ReqdColltnDt");
        DrctDbtTxInf5.appendChild(ReqdColltnDt5);
        ReqdColltnDt5.setTextContent(formattedNextDay);
        
        
        Element DrctDbtTx5 = doc.createElement("DrctDbtTx");
        DrctDbtTxInf5.appendChild(DrctDbtTx5);
        Element MndtRltdInf5 = doc.createElement("MndtRltdInf");
        DrctDbtTx5.appendChild(MndtRltdInf5);
        Element MndtId5 = doc.createElement("MndtId");
        MndtRltdInf5.appendChild(MndtId5);
        MndtId5.setTextContent("85055");
        
        Element DtOfSgntr5 = doc.createElement("DtOfSgntr");
        MndtRltdInf5.appendChild(DtOfSgntr5);
        DtOfSgntr5.setTextContent(formattedNextDay);
        
        Element AmdmntInd5 = doc.createElement("AmdmntInd");
        MndtRltdInf5.appendChild(AmdmntInd5);
        AmdmntInd5.setTextContent("true");
        
        
        //AmdmntInfDtls
        Element AmdmntInfDtls5 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf5.appendChild(AmdmntInfDtls5);
        Element OrgnlCdtrSchmeId5 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls5.appendChild(OrgnlCdtrSchmeId5);
        Element Nm5 = doc.createElement("Nm");
        OrgnlCdtrSchmeId5.appendChild(Nm5);
        Nm5.setTextContent("Vodafone Co");
        
        Element Id5 = doc.createElement("Id");
        OrgnlCdtrSchmeId5.appendChild(Id5);
        //PrvtId
        
        Element PrvtId5 = doc.createElement("PrvtId");
        Id5.appendChild(PrvtId5);
        //Othr
        Element Othr5 = doc.createElement("Othr");
        PrvtId5.appendChild(Othr5);
        Element Id5_5 = doc.createElement("Id");
        Othr5.appendChild(Id5_5);
        Id5_5.setTextContent("NL58ZZZ550750050000");
        
       
        //SchmeNm
        Element SchmeNm5 = doc.createElement("SchmeNm");
        Othr5.appendChild(SchmeNm5);
        Element Prtry5 = doc.createElement("Prtry");
        SchmeNm5.appendChild(Prtry5);
        Prtry5.setTextContent("SEPA");
        
        
        //OrgnlDbtrAcct
        Element OrgnlDbtrAcct5 = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls5.appendChild(OrgnlDbtrAcct5);
        Element Id_5_5_5 = doc.createElement("Id");
        OrgnlDbtrAcct5.appendChild(Id_5_5_5);
        Element IBAN5__5 = doc.createElement("IBAN");
        Id_5_5_5.appendChild(IBAN5__5);
        IBAN5__5.setTextContent("NL85NNBA0759055855");
        
        //Tag ElctrncSgntr5
        Element ElctrncSgntr5 = doc.createElement("ElctrncSgntr");
        MndtRltdInf5.appendChild(ElctrncSgntr5);
        ElctrncSgntr5.setTextContent("DIANA");
        
        //Tag CdtrSchmeId
        Element CdtrSchmeId5 = doc.createElement("CdtrSchmeId");
        DrctDbtTx5.appendChild(CdtrSchmeId5);
        Element Id_New_5 = doc.createElement("Id");
        CdtrSchmeId5.appendChild(Id_New_5);
        Element PrvtId5_5 = doc.createElement("PrvtId");
        Id_New_5.appendChild(PrvtId5_5);
        Element Othr_5 = doc.createElement("Othr");
        PrvtId5_5.appendChild(Othr_5);
        Element Id_New_5_5 = doc.createElement("Id");
        Othr_5.appendChild(Id_New_5_5);
        Id_New_5_5.setTextContent("NL58ZZZ550750050000");
        
        //Tag SchmeNm
//        <SchmeNm>
//        <Prtry>SEPA</Prtry>
//        </SchmeNm>
        
        Element SchmeNm5_5 = doc.createElement("SchmeNm");
        Othr_5.appendChild(SchmeNm5_5);
        Element Prtry5_5 = doc.createElement("Prtry");
        SchmeNm5_5.appendChild(Prtry5_5);
        Prtry5_5.setTextContent("SEPA");
        
        
        //Tag Cdtr
        Element Cdtr5 = doc.createElement("Cdtr");
        DrctDbtTxInf5.appendChild(Cdtr5);
        Element Nm5_New = doc.createElement("Nm");
        Cdtr5.appendChild(Nm5_New);
        Nm5_New.setTextContent("Vodafon Co");
        
        //Tag PstlAdr
        Element PstlAdr5 = doc.createElement("PstlAdr");
        Cdtr5.appendChild(PstlAdr5);
        Element Ctry5 = doc.createElement("Ctry");
        PstlAdr5.appendChild(Ctry5);
        Ctry5.setTextContent("NL");
        //<AdrLine>s Gravenhof 2 D</AdrLine>
        //<AdrLine>7505 DN ZUTPHEN</AdrLine>
        Element AdrLine5 = doc.createElement("AdrLine");
        PstlAdr5.appendChild(AdrLine5);
        AdrLine5.setTextContent("s Gravenhof 2 D");
        Element AdrLine5_5 = doc.createElement("AdrLine");
        PstlAdr5.appendChild(AdrLine5_5);
        AdrLine5_5.setTextContent("7505 DN ZUTPHEN");
        
        
        
        //Tag CdtrAcct
        Element CdtrAcct5 = doc.createElement("CdtrAcct");
        DrctDbtTxInf5.appendChild(CdtrAcct5);
        Element Id_5_Acct = doc.createElement("Id");
        CdtrAcct5.appendChild(Id_5_Acct);
        Element IBAN_5 = doc.createElement("IBAN");
        Id_5_Acct.appendChild(IBAN_5);
        IBAN_5.setTextContent("NL55RABO0500005555");
        
        //Tag CdtrAgt
        Element CdtrAgt5 = doc.createElement("CdtrAgt");
        DrctDbtTxInf5.appendChild(CdtrAgt5);
        Element FinInstnId5 = doc.createElement("FinInstnId");
        CdtrAgt5.appendChild(FinInstnId5);
        Element BICFI5_New = doc.createElement("BICFI");
        FinInstnId5.appendChild(BICFI5_New);
        BICFI5_New.setTextContent("RABONL5U");
        
        //Tag UltmtCdtr
        Element UltmtCdtr5 = doc.createElement("UltmtCdtr");
        DrctDbtTxInf5.appendChild(UltmtCdtr5);
        Element Nm5_5 = doc.createElement("Nm");
        UltmtCdtr5.appendChild(Nm5_5);
        Nm5_5.setTextContent("str1234");
        
        //Tag PstlAdr
        Element PstlAdr5_5 = doc.createElement("PstlAdr");
        UltmtCdtr5.appendChild(PstlAdr5_5);
        Element Dept5 = doc.createElement("Dept");
        PstlAdr5_5.appendChild(Dept5);
        Dept5.setTextContent("str1234");
        
        //Tag SubDept
        Element SubDept5 = doc.createElement("SubDept");
        PstlAdr5_5.appendChild(SubDept5);
        SubDept5.setTextContent("str1234");
        
        Element StrtNm5 = doc.createElement("StrtNm");
        PstlAdr5_5.appendChild(StrtNm5);
        StrtNm5.setTextContent("str1234");
        
        Element BldgNb5 = doc.createElement("BldgNb");
        PstlAdr5_5.appendChild(BldgNb5);
        BldgNb5.setTextContent("str1234");
        
        Element PstCd5 = doc.createElement("PstCd");
        PstlAdr5_5.appendChild(PstCd5);
        PstCd5.setTextContent("str1234");
        
        Element TwnNm5 = doc.createElement("TwnNm");
        PstlAdr5_5.appendChild(TwnNm5);
        TwnNm5.setTextContent("str1234");
        
        Element CtrySubDvsn5 = doc.createElement("CtrySubDvsn");
        PstlAdr5_5.appendChild(CtrySubDvsn5);
        CtrySubDvsn5.setTextContent("str1234");
        
        
        Element Ctry5_5 = doc.createElement("Ctry");
        PstlAdr5_5.appendChild(Ctry5_5);
        Ctry5_5.setTextContent("NL");
        
        //Tag Id
        Element Id_new5 = doc.createElement("Id");
        UltmtCdtr5.appendChild(Id_new5);
        Element OrgId5 = doc.createElement("OrgId");
        Id_new5.appendChild(OrgId5);
        Element AnyBIC5 = doc.createElement("AnyBIC");
        OrgId5.appendChild(AnyBIC5);
        AnyBIC5.setTextContent("RABONL5U");
        
        //Tag InstgAgt
        
        Element InstgAgt5 = doc.createElement("InstgAgt");
        DrctDbtTxInf5.appendChild(InstgAgt5);
        Element FinInstnId5_New = doc.createElement("FinInstnId");
        InstgAgt5.appendChild(FinInstnId5_New);
        Element BICFI5_5 = doc.createElement("BICFI");
        FinInstnId5_New.appendChild(BICFI5_5);
        BICFI5_5.setTextContent("RABONL5U");
        
        //Tag Dbtr
        Element Dbtr5_New = doc.createElement("Dbtr");
        DrctDbtTxInf5.appendChild(Dbtr5_New);
        Element Nm_5 = doc.createElement("Nm");
        Dbtr5_New.appendChild(Nm_5);
        Nm_5.setTextContent("D. Demir Halk Debtor");
        
        //Tag DbtrAcct
        
        Element DbtrAcct5 = doc.createElement("DbtrAcct");
        DrctDbtTxInf5.appendChild(DbtrAcct5);
        Element Id_5_New = doc.createElement("Id");
        DbtrAcct5.appendChild(Id_5_New);
        Element IBAN5 = doc.createElement("IBAN");
        Id_5_New.appendChild(IBAN5);
        IBAN5.setTextContent("NL85NNBA0759055855");
        
        //Tag DbtrAgt
        Element DbtrAgt5 = doc.createElement("DbtrAgt");
        DrctDbtTxInf5.appendChild(DbtrAgt5);
        Element FinInstnId5_5 = doc.createElement("FinInstnId");
        DbtrAgt5.appendChild(FinInstnId5_5);
        Element BICFI5_5_5 = doc.createElement("BICFI");
        FinInstnId5_5.appendChild(BICFI5_5_5);
        BICFI5_5_5.setTextContent("NNBANL5G");
        
        //Tag UltmtDbtr
        
        Element UltmtDbtr5 = doc.createElement("UltmtDbtr");
        DrctDbtTxInf5.appendChild(UltmtDbtr5);
        Element Nm5_5_5 = doc.createElement("Nm");
        UltmtDbtr5.appendChild(Nm5_5_5);
        Nm5_5_5.setTextContent("str1234");
        
        //Tag PstlAdr
        
        Element PstlAdr5_5_5 = doc.createElement("PstlAdr");
        UltmtDbtr5.appendChild(PstlAdr5_5_5);
        //PstlAdr5_5_5.setTextContent("str1234");
        
        Element Dept5_5 = doc.createElement("Dept");
        PstlAdr5_5_5.appendChild(Dept5_5);
        Dept5_5.setTextContent("str1234");
        
        Element SubDept5_5 = doc.createElement("SubDept");
        PstlAdr5_5_5.appendChild(SubDept5_5);
        SubDept5_5.setTextContent("str1234");
        
        Element StrtNm5_5 = doc.createElement("StrtNm");
        PstlAdr5_5_5.appendChild(StrtNm5_5);
        StrtNm5_5.setTextContent("str1234");
        
        Element BldgNb5_5 = doc.createElement("BldgNb");
        PstlAdr5_5_5.appendChild(BldgNb5_5);
        BldgNb5_5.setTextContent("str1234");
        
        Element PstCd5_5 = doc.createElement("PstCd");
        PstlAdr5_5_5.appendChild(PstCd5_5);
        PstCd5_5.setTextContent("str1234");
        
        Element TwnNm5_5 = doc.createElement("TwnNm");
        PstlAdr5_5_5.appendChild(TwnNm5_5);
        TwnNm5_5.setTextContent("str1234");
        
        Element CtrySubDvsn5_5 = doc.createElement("CtrySubDvsn");
        PstlAdr5_5_5.appendChild(CtrySubDvsn5_5);
        CtrySubDvsn5_5.setTextContent("str1234");
        
        Element Ctry55 = doc.createElement("Ctry");
        PstlAdr5_5_5.appendChild(Ctry55);
        Ctry55.setTextContent("str1234");
        
        Element Id_Inside5 = doc.createElement("Id");
        UltmtDbtr5.appendChild(Id_Inside5);
        
        
        
        //Tag OrgId
        Element OrgId5_5 = doc.createElement("OrgId");
        Id_Inside5.appendChild(OrgId5_5);
        Element AnyBIC5_5 = doc.createElement("AnyBIC");
        OrgId5_5.appendChild(AnyBIC5_5);
        AnyBIC5_5.setTextContent("RABONL5U");
        
        //RmtInf
        Element RmtInf5 = doc.createElement("RmtInf");
        DrctDbtTxInf5.appendChild(RmtInf5);
        Element Ustrd5 = doc.createElement("Ustrd");
        RmtInf5.appendChild(Ustrd5);
        Ustrd5.setTextContent("Direct Debit Collection");
        
        //Amount6

        
        Element DrctDbtTxInf6 = doc.createElement("DrctDbtTxInf");
        FIToFICstmrDrctDbt.appendChild(DrctDbtTxInf6);
        Element PmtId6 = doc.createElement("PmtId");
        DrctDbtTxInf6.appendChild(PmtId6);
        Element InstrId6 = doc.createElement("InstrId");
        PmtId6.appendChild(InstrId6);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf6 = doc.createElement("PmtTpInf");
        DrctDbtTxInf6.appendChild(PmtTpInf6);
        Element SvcLvl6 = doc.createElement("SvcLvl");
        PmtTpInf6.appendChild(SvcLvl6);
        Element Cd6 = doc.createElement("Cd");
        SvcLvl6.appendChild(Cd6);
        Cd6.setTextContent("SEPA");
        
        //tag LclInstrm
        
        //Element PmtTpInf6 = doc.createElement("PmtTpInf");
        //DrctDbtTxInf6.appendChild(PmtTpInf6);
        Element LclInstrm6 = doc.createElement("LclInstrm");
        PmtTpInf6.appendChild(LclInstrm6);
        Element Cd6_ = doc.createElement("Cd");
        LclInstrm6.appendChild(Cd6_);
        Cd6_.setTextContent("CORE");
        Element SeqTp6 = doc.createElement("SeqTp");
        PmtTpInf6.appendChild(SeqTp6);
        SeqTp6.setTextContent("RCUR");
        
        //tag CtgyPurp
        Element CtgyPurp6 = doc.createElement("CtgyPurp");
        PmtTpInf6.appendChild(CtgyPurp6);
        Element Cd6_6 = doc.createElement("Cd");
        CtgyPurp6.appendChild(Cd6_6);
        Cd6_6.setTextContent("str1");
        
        InstrId6.setTextContent(MsgIdText);
        Element EndToEndId6 = doc.createElement("EndToEndId");
        PmtId6.appendChild(EndToEndId6);
        //String EndToEndIdText = "E1E"+Messagetime111;
        EndToEndId6.setTextContent(EndToEndIdText);
        Element TxId6 = doc.createElement("TxId");
        PmtId6.appendChild(TxId6);
        TxId6.setTextContent("TXDIDI1A100811F"+Messagetime111);
        
        
        Element IntrBkSttlmAmt6 = doc.createElement("IntrBkSttlmAmt");
        DrctDbtTxInf6.appendChild(IntrBkSttlmAmt6);
        IntrBkSttlmAmt6.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt6.setTextContent("16");
        Element ChrgBr6 = doc.createElement("ChrgBr");
        DrctDbtTxInf6.appendChild(ChrgBr6);
        ChrgBr6.setTextContent("SLEV");
        
        //ReqdColltnDt
        Element ReqdColltnDt6 = doc.createElement("ReqdColltnDt");
        DrctDbtTxInf6.appendChild(ReqdColltnDt6);
        ReqdColltnDt6.setTextContent(formattedNextDay);
        
        
        Element DrctDbtTx6 = doc.createElement("DrctDbtTx");
        DrctDbtTxInf6.appendChild(DrctDbtTx6);
        Element MndtRltdInf6 = doc.createElement("MndtRltdInf");
        DrctDbtTx6.appendChild(MndtRltdInf6);
        Element MndtId6 = doc.createElement("MndtId");
        MndtRltdInf6.appendChild(MndtId6);
        MndtId6.setTextContent("86066");
        
        Element DtOfSgntr6 = doc.createElement("DtOfSgntr");
        MndtRltdInf6.appendChild(DtOfSgntr6);
        DtOfSgntr6.setTextContent(formattedNextDay);
        
        Element AmdmntInd6 = doc.createElement("AmdmntInd");
        MndtRltdInf6.appendChild(AmdmntInd6);
        AmdmntInd6.setTextContent("true");
        
        
        //AmdmntInfDtls
        Element AmdmntInfDtls6 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf6.appendChild(AmdmntInfDtls6);
        Element OrgnlCdtrSchmeId6 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls6.appendChild(OrgnlCdtrSchmeId6);
        Element Nm6 = doc.createElement("Nm");
        OrgnlCdtrSchmeId6.appendChild(Nm6);
        Nm6.setTextContent("Vodafone Co");
        
        Element Id6 = doc.createElement("Id");
        OrgnlCdtrSchmeId6.appendChild(Id6);
        //PrvtId
        
        Element PrvtId6 = doc.createElement("PrvtId");
        Id6.appendChild(PrvtId6);
        //Othr
        Element Othr6 = doc.createElement("Othr");
        PrvtId6.appendChild(Othr6);
        Element Id6_6 = doc.createElement("Id");
        Othr6.appendChild(Id6_6);
        Id6_6.setTextContent("NL68ZZZ660760060000");
        
       
        //SchmeNm
        Element SchmeNm6 = doc.createElement("SchmeNm");
        Othr6.appendChild(SchmeNm6);
        Element Prtry6 = doc.createElement("Prtry");
        SchmeNm6.appendChild(Prtry6);
        Prtry6.setTextContent("SEPA");
        
        
        //OrgnlDbtrAcct
        Element OrgnlDbtrAcct6 = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls6.appendChild(OrgnlDbtrAcct6);
        Element Id_6_6_6 = doc.createElement("Id");
        OrgnlDbtrAcct6.appendChild(Id_6_6_6);
        Element IBAN6__6 = doc.createElement("IBAN");
        Id_6_6_6.appendChild(IBAN6__6);
        IBAN6__6.setTextContent("NL86NNBA0769066866");
        
        //Tag ElctrncSgntr6
        Element ElctrncSgntr6 = doc.createElement("ElctrncSgntr");
        MndtRltdInf6.appendChild(ElctrncSgntr6);
        ElctrncSgntr6.setTextContent("DIANA");
        
        //Tag CdtrSchmeId
        Element CdtrSchmeId6 = doc.createElement("CdtrSchmeId");
        DrctDbtTx6.appendChild(CdtrSchmeId6);
        Element Id_New_6 = doc.createElement("Id");
        CdtrSchmeId6.appendChild(Id_New_6);
        Element PrvtId6_6 = doc.createElement("PrvtId");
        Id_New_6.appendChild(PrvtId6_6);
        Element Othr_6 = doc.createElement("Othr");
        PrvtId6_6.appendChild(Othr_6);
        Element Id_New_6_6 = doc.createElement("Id");
        Othr_6.appendChild(Id_New_6_6);
        Id_New_6_6.setTextContent("NL68ZZZ660760060000");
        
        //Tag SchmeNm
//        <SchmeNm>
//        <Prtry>SEPA</Prtry>
//        </SchmeNm>
        
        Element SchmeNm6_6 = doc.createElement("SchmeNm");
        Othr_6.appendChild(SchmeNm6_6);
        Element Prtry6_6 = doc.createElement("Prtry");
        SchmeNm6_6.appendChild(Prtry6_6);
        Prtry6_6.setTextContent("SEPA");
        
        
        //Tag Cdtr
        Element Cdtr6 = doc.createElement("Cdtr");
        DrctDbtTxInf6.appendChild(Cdtr6);
        Element Nm6_New = doc.createElement("Nm");
        Cdtr6.appendChild(Nm6_New);
        Nm6_New.setTextContent("Vodafon Co");
        
        //Tag PstlAdr
        Element PstlAdr6 = doc.createElement("PstlAdr");
        Cdtr6.appendChild(PstlAdr6);
        Element Ctry6 = doc.createElement("Ctry");
        PstlAdr6.appendChild(Ctry6);
        Ctry6.setTextContent("NL");
        //<AdrLine>s Gravenhof 2 D</AdrLine>
        //<AdrLine>7606 DN ZUTPHEN</AdrLine>
        Element AdrLine6 = doc.createElement("AdrLine");
        PstlAdr6.appendChild(AdrLine6);
        AdrLine6.setTextContent("s Gravenhof 2 D");
        Element AdrLine6_6 = doc.createElement("AdrLine");
        PstlAdr6.appendChild(AdrLine6_6);
        AdrLine6_6.setTextContent("7606 DN ZUTPHEN");
        
        
        
        //Tag CdtrAcct
        Element CdtrAcct6 = doc.createElement("CdtrAcct");
        DrctDbtTxInf6.appendChild(CdtrAcct6);
        Element Id_6_Acct = doc.createElement("Id");
        CdtrAcct6.appendChild(Id_6_Acct);
        Element IBAN_6 = doc.createElement("IBAN");
        Id_6_Acct.appendChild(IBAN_6);
        IBAN_6.setTextContent("NL66RABO0600006666");
        
        //Tag CdtrAgt
        Element CdtrAgt6 = doc.createElement("CdtrAgt");
        DrctDbtTxInf6.appendChild(CdtrAgt6);
        Element FinInstnId6 = doc.createElement("FinInstnId");
        CdtrAgt6.appendChild(FinInstnId6);
        Element BICFI6_New = doc.createElement("BICFI");
        FinInstnId6.appendChild(BICFI6_New);
        BICFI6_New.setTextContent("RABONL6U");
        
        //Tag UltmtCdtr
        Element UltmtCdtr6 = doc.createElement("UltmtCdtr");
        DrctDbtTxInf6.appendChild(UltmtCdtr6);
        Element Nm6_6 = doc.createElement("Nm");
        UltmtCdtr6.appendChild(Nm6_6);
        Nm6_6.setTextContent("str1234");
        
        //Tag PstlAdr
        Element PstlAdr6_6 = doc.createElement("PstlAdr");
        UltmtCdtr6.appendChild(PstlAdr6_6);
        Element Dept6 = doc.createElement("Dept");
        PstlAdr6_6.appendChild(Dept6);
        Dept6.setTextContent("str1234");
        
        //Tag SubDept
        Element SubDept6 = doc.createElement("SubDept");
        PstlAdr6_6.appendChild(SubDept6);
        SubDept6.setTextContent("str1234");
        
        Element StrtNm6 = doc.createElement("StrtNm");
        PstlAdr6_6.appendChild(StrtNm6);
        StrtNm6.setTextContent("str1234");
        
        Element BldgNb6 = doc.createElement("BldgNb");
        PstlAdr6_6.appendChild(BldgNb6);
        BldgNb6.setTextContent("str1234");
        
        Element PstCd6 = doc.createElement("PstCd");
        PstlAdr6_6.appendChild(PstCd6);
        PstCd6.setTextContent("str1234");
        
        Element TwnNm6 = doc.createElement("TwnNm");
        PstlAdr6_6.appendChild(TwnNm6);
        TwnNm6.setTextContent("str1234");
        
        Element CtrySubDvsn6 = doc.createElement("CtrySubDvsn");
        PstlAdr6_6.appendChild(CtrySubDvsn6);
        CtrySubDvsn6.setTextContent("str1234");
        
        
        Element Ctry6_6 = doc.createElement("Ctry");
        PstlAdr6_6.appendChild(Ctry6_6);
        Ctry6_6.setTextContent("NL");
        
        //Tag Id
        Element Id_new6 = doc.createElement("Id");
        UltmtCdtr6.appendChild(Id_new6);
        Element OrgId6 = doc.createElement("OrgId");
        Id_new6.appendChild(OrgId6);
        Element AnyBIC6 = doc.createElement("AnyBIC");
        OrgId6.appendChild(AnyBIC6);
        AnyBIC6.setTextContent("RABONL6U");
        
        //Tag InstgAgt
        
        Element InstgAgt6 = doc.createElement("InstgAgt");
        DrctDbtTxInf6.appendChild(InstgAgt6);
        Element FinInstnId6_New = doc.createElement("FinInstnId");
        InstgAgt6.appendChild(FinInstnId6_New);
        Element BICFI6_6 = doc.createElement("BICFI");
        FinInstnId6_New.appendChild(BICFI6_6);
        BICFI6_6.setTextContent("RABONL6U");
        
        //Tag Dbtr
        Element Dbtr6_New = doc.createElement("Dbtr");
        DrctDbtTxInf6.appendChild(Dbtr6_New);
        Element Nm_6 = doc.createElement("Nm");
        Dbtr6_New.appendChild(Nm_6);
        Nm_6.setTextContent("D. Demir Halk Debtor");
        
        //Tag DbtrAcct
        
        Element DbtrAcct6 = doc.createElement("DbtrAcct");
        DrctDbtTxInf6.appendChild(DbtrAcct6);
        Element Id_6_New = doc.createElement("Id");
        DbtrAcct6.appendChild(Id_6_New);
        Element IBAN6 = doc.createElement("IBAN");
        Id_6_New.appendChild(IBAN6);
        IBAN6.setTextContent("NL86NNBA0769066866");
        
        //Tag DbtrAgt
        Element DbtrAgt6 = doc.createElement("DbtrAgt");
        DrctDbtTxInf6.appendChild(DbtrAgt6);
        Element FinInstnId6_6 = doc.createElement("FinInstnId");
        DbtrAgt6.appendChild(FinInstnId6_6);
        Element BICFI6_6_6 = doc.createElement("BICFI");
        FinInstnId6_6.appendChild(BICFI6_6_6);
        BICFI6_6_6.setTextContent("NNBANL6G");
        
        //Tag UltmtDbtr
        
        Element UltmtDbtr6 = doc.createElement("UltmtDbtr");
        DrctDbtTxInf6.appendChild(UltmtDbtr6);
        Element Nm6_6_6 = doc.createElement("Nm");
        UltmtDbtr6.appendChild(Nm6_6_6);
        Nm6_6_6.setTextContent("str1234");
        
        //Tag PstlAdr
        
        Element PstlAdr6_6_6 = doc.createElement("PstlAdr");
        UltmtDbtr6.appendChild(PstlAdr6_6_6);
        //PstlAdr6_6_6.setTextContent("str1234");
        
        Element Dept6_6 = doc.createElement("Dept");
        PstlAdr6_6_6.appendChild(Dept6_6);
        Dept6_6.setTextContent("str1234");
        
        Element SubDept6_6 = doc.createElement("SubDept");
        PstlAdr6_6_6.appendChild(SubDept6_6);
        SubDept6_6.setTextContent("str1234");
        
        Element StrtNm6_6 = doc.createElement("StrtNm");
        PstlAdr6_6_6.appendChild(StrtNm6_6);
        StrtNm6_6.setTextContent("str1234");
        
        Element BldgNb6_6 = doc.createElement("BldgNb");
        PstlAdr6_6_6.appendChild(BldgNb6_6);
        BldgNb6_6.setTextContent("str1234");
        
        Element PstCd6_6 = doc.createElement("PstCd");
        PstlAdr6_6_6.appendChild(PstCd6_6);
        PstCd6_6.setTextContent("str1234");
        
        Element TwnNm6_6 = doc.createElement("TwnNm");
        PstlAdr6_6_6.appendChild(TwnNm6_6);
        TwnNm6_6.setTextContent("str1234");
        
        Element CtrySubDvsn6_6 = doc.createElement("CtrySubDvsn");
        PstlAdr6_6_6.appendChild(CtrySubDvsn6_6);
        CtrySubDvsn6_6.setTextContent("str1234");
        
        Element Ctry66 = doc.createElement("Ctry");
        PstlAdr6_6_6.appendChild(Ctry66);
        Ctry66.setTextContent("str1234");
        
        Element Id_Inside6 = doc.createElement("Id");
        UltmtDbtr6.appendChild(Id_Inside6);
        
        
        
        //Tag OrgId
        Element OrgId6_6 = doc.createElement("OrgId");
        Id_Inside6.appendChild(OrgId6_6);
        Element AnyBIC6_6 = doc.createElement("AnyBIC");
        OrgId6_6.appendChild(AnyBIC6_6);
        AnyBIC6_6.setTextContent("RABONL2U");
        
        //RmtInf
        Element RmtInf6 = doc.createElement("RmtInf");
        DrctDbtTxInf6.appendChild(RmtInf6);
        Element Ustrd6 = doc.createElement("Ustrd");
        RmtInf6.appendChild(Ustrd6);
        Ustrd6.setTextContent("Direct Debit Collection");
        
        //Amount7
        

        
        Element DrctDbtTxInf7 = doc.createElement("DrctDbtTxInf");
        FIToFICstmrDrctDbt.appendChild(DrctDbtTxInf7);
        Element PmtId7 = doc.createElement("PmtId");
        DrctDbtTxInf7.appendChild(PmtId7);
        Element InstrId7 = doc.createElement("InstrId");
        PmtId7.appendChild(InstrId7);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf7 = doc.createElement("PmtTpInf");
        DrctDbtTxInf7.appendChild(PmtTpInf7);
        Element SvcLvl7 = doc.createElement("SvcLvl");
        PmtTpInf7.appendChild(SvcLvl7);
        Element Cd7 = doc.createElement("Cd");
        SvcLvl7.appendChild(Cd7);
        Cd7.setTextContent("SEPA");
        
        //tag LclInstrm
        
        //Element PmtTpInf7 = doc.createElement("PmtTpInf");
        //DrctDbtTxInf7.appendChild(PmtTpInf7);
        Element LclInstrm7 = doc.createElement("LclInstrm");
        PmtTpInf7.appendChild(LclInstrm7);
        Element Cd7_ = doc.createElement("Cd");
        LclInstrm7.appendChild(Cd7_);
        Cd7_.setTextContent("CORE");
        Element SeqTp7 = doc.createElement("SeqTp");
        PmtTpInf7.appendChild(SeqTp7);
        SeqTp7.setTextContent("RCUR");
        
        //tag CtgyPurp
        Element CtgyPurp7 = doc.createElement("CtgyPurp");
        PmtTpInf7.appendChild(CtgyPurp7);
        Element Cd7_7 = doc.createElement("Cd");
        CtgyPurp7.appendChild(Cd7_7);
        Cd7_7.setTextContent("str1");
        
        InstrId7.setTextContent(MsgIdText);
        Element EndToEndId7 = doc.createElement("EndToEndId");
        PmtId7.appendChild(EndToEndId7);
        //String EndToEndIdText = "E1E"+Messagetime111;
        EndToEndId7.setTextContent(EndToEndIdText);
        Element TxId7 = doc.createElement("TxId");
        PmtId7.appendChild(TxId7);
        TxId7.setTextContent("TXDIDI1A100811F"+Messagetime111);
        
        
        Element IntrBkSttlmAmt7 = doc.createElement("IntrBkSttlmAmt");
        DrctDbtTxInf7.appendChild(IntrBkSttlmAmt7);
        IntrBkSttlmAmt7.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt7.setTextContent("17");
        Element ChrgBr7 = doc.createElement("ChrgBr");
        DrctDbtTxInf7.appendChild(ChrgBr7);
        ChrgBr7.setTextContent("SLEV");
        
        //ReqdColltnDt
        Element ReqdColltnDt7 = doc.createElement("ReqdColltnDt");
        DrctDbtTxInf7.appendChild(ReqdColltnDt7);
        ReqdColltnDt7.setTextContent(formattedNextDay);
        
        
        Element DrctDbtTx7 = doc.createElement("DrctDbtTx");
        DrctDbtTxInf7.appendChild(DrctDbtTx7);
        Element MndtRltdInf7 = doc.createElement("MndtRltdInf");
        DrctDbtTx7.appendChild(MndtRltdInf7);
        Element MndtId7 = doc.createElement("MndtId");
        MndtRltdInf7.appendChild(MndtId7);
        MndtId7.setTextContent("87077");
        
        Element DtOfSgntr7 = doc.createElement("DtOfSgntr");
        MndtRltdInf7.appendChild(DtOfSgntr7);
        DtOfSgntr7.setTextContent(formattedNextDay);
        
        Element AmdmntInd7 = doc.createElement("AmdmntInd");
        MndtRltdInf7.appendChild(AmdmntInd7);
        AmdmntInd7.setTextContent("true");
        
        
        //AmdmntInfDtls
        Element AmdmntInfDtls7 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf7.appendChild(AmdmntInfDtls7);
        Element OrgnlCdtrSchmeId7 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls7.appendChild(OrgnlCdtrSchmeId7);
        Element Nm7 = doc.createElement("Nm");
        OrgnlCdtrSchmeId7.appendChild(Nm7);
        Nm7.setTextContent("Vodafone Co");
        
        Element Id7 = doc.createElement("Id");
        OrgnlCdtrSchmeId7.appendChild(Id7);
        //PrvtId
        
        Element PrvtId7 = doc.createElement("PrvtId");
        Id7.appendChild(PrvtId7);
        //Othr
        Element Othr7 = doc.createElement("Othr");
        PrvtId7.appendChild(Othr7);
        Element Id7_7 = doc.createElement("Id");
        Othr7.appendChild(Id7_7);
        Id7_7.setTextContent("NL78ZZZ770770070000");
        
       
        //SchmeNm
        Element SchmeNm7 = doc.createElement("SchmeNm");
        Othr7.appendChild(SchmeNm7);
        Element Prtry7 = doc.createElement("Prtry");
        SchmeNm7.appendChild(Prtry7);
        Prtry7.setTextContent("SEPA");
        
        
        //OrgnlDbtrAcct
        Element OrgnlDbtrAcct7 = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls7.appendChild(OrgnlDbtrAcct7);
        Element Id_7_7_7 = doc.createElement("Id");
        OrgnlDbtrAcct7.appendChild(Id_7_7_7);
        Element IBAN7__7 = doc.createElement("IBAN");
        Id_7_7_7.appendChild(IBAN7__7);
        IBAN7__7.setTextContent("NL87NNBA0779077877");
        
        //Tag ElctrncSgntr7
        Element ElctrncSgntr7 = doc.createElement("ElctrncSgntr");
        MndtRltdInf7.appendChild(ElctrncSgntr7);
        ElctrncSgntr7.setTextContent("DIANA");
        
        //Tag CdtrSchmeId
        Element CdtrSchmeId7 = doc.createElement("CdtrSchmeId");
        DrctDbtTx7.appendChild(CdtrSchmeId7);
        Element Id_New_7 = doc.createElement("Id");
        CdtrSchmeId7.appendChild(Id_New_7);
        Element PrvtId7_7 = doc.createElement("PrvtId");
        Id_New_7.appendChild(PrvtId7_7);
        Element Othr_7 = doc.createElement("Othr");
        PrvtId7_7.appendChild(Othr_7);
        Element Id_New_7_7 = doc.createElement("Id");
        Othr_7.appendChild(Id_New_7_7);
        Id_New_7_7.setTextContent("NL78ZZZ770770070000");
        
        //Tag SchmeNm
//        <SchmeNm>
//        <Prtry>SEPA</Prtry>
//        </SchmeNm>
        
        Element SchmeNm7_7 = doc.createElement("SchmeNm");
        Othr_7.appendChild(SchmeNm7_7);
        Element Prtry7_7 = doc.createElement("Prtry");
        SchmeNm7_7.appendChild(Prtry7_7);
        Prtry7_7.setTextContent("SEPA");
        
        
        //Tag Cdtr
        Element Cdtr7 = doc.createElement("Cdtr");
        DrctDbtTxInf7.appendChild(Cdtr7);
        Element Nm7_New = doc.createElement("Nm");
        Cdtr7.appendChild(Nm7_New);
        Nm7_New.setTextContent("Vodafon Co");
        
        //Tag PstlAdr
        Element PstlAdr7 = doc.createElement("PstlAdr");
        Cdtr7.appendChild(PstlAdr7);
        Element Ctry7 = doc.createElement("Ctry");
        PstlAdr7.appendChild(Ctry7);
        Ctry7.setTextContent("NL");
        //<AdrLine>s Gravenhof 2 D</AdrLine>
        //<AdrLine>7707 DN ZUTPHEN</AdrLine>
        Element AdrLine7 = doc.createElement("AdrLine");
        PstlAdr7.appendChild(AdrLine7);
        AdrLine7.setTextContent("s Gravenhof 2 D");
        Element AdrLine7_7 = doc.createElement("AdrLine");
        PstlAdr7.appendChild(AdrLine7_7);
        AdrLine7_7.setTextContent("7707 DN ZUTPHEN");
        
        
        
        //Tag CdtrAcct
        Element CdtrAcct7 = doc.createElement("CdtrAcct");
        DrctDbtTxInf7.appendChild(CdtrAcct7);
        Element Id_7_Acct = doc.createElement("Id");
        CdtrAcct7.appendChild(Id_7_Acct);
        Element IBAN_7 = doc.createElement("IBAN");
        Id_7_Acct.appendChild(IBAN_7);
        IBAN_7.setTextContent("NL77RABO0700007777");
        
        //Tag CdtrAgt
        Element CdtrAgt7 = doc.createElement("CdtrAgt");
        DrctDbtTxInf7.appendChild(CdtrAgt7);
        Element FinInstnId7 = doc.createElement("FinInstnId");
        CdtrAgt7.appendChild(FinInstnId7);
        Element BICFI7_New = doc.createElement("BICFI");
        FinInstnId7.appendChild(BICFI7_New);
        BICFI7_New.setTextContent("RABONL7U");
        
        //Tag UltmtCdtr
        Element UltmtCdtr7 = doc.createElement("UltmtCdtr");
        DrctDbtTxInf7.appendChild(UltmtCdtr7);
        Element Nm7_7 = doc.createElement("Nm");
        UltmtCdtr7.appendChild(Nm7_7);
        Nm7_7.setTextContent("str1234");
        
        //Tag PstlAdr
        Element PstlAdr7_7 = doc.createElement("PstlAdr");
        UltmtCdtr7.appendChild(PstlAdr7_7);
        Element Dept7 = doc.createElement("Dept");
        PstlAdr7_7.appendChild(Dept7);
        Dept7.setTextContent("str1234");
        
        //Tag SubDept
        Element SubDept7 = doc.createElement("SubDept");
        PstlAdr7_7.appendChild(SubDept7);
        SubDept7.setTextContent("str1234");
        
        Element StrtNm7 = doc.createElement("StrtNm");
        PstlAdr7_7.appendChild(StrtNm7);
        StrtNm7.setTextContent("str1234");
        
        Element BldgNb7 = doc.createElement("BldgNb");
        PstlAdr7_7.appendChild(BldgNb7);
        BldgNb7.setTextContent("str1234");
        
        Element PstCd7 = doc.createElement("PstCd");
        PstlAdr7_7.appendChild(PstCd7);
        PstCd7.setTextContent("str1234");
        
        Element TwnNm7 = doc.createElement("TwnNm");
        PstlAdr7_7.appendChild(TwnNm7);
        TwnNm7.setTextContent("str1234");
        
        Element CtrySubDvsn7 = doc.createElement("CtrySubDvsn");
        PstlAdr7_7.appendChild(CtrySubDvsn7);
        CtrySubDvsn7.setTextContent("str1234");
        
        
        Element Ctry7_7 = doc.createElement("Ctry");
        PstlAdr7_7.appendChild(Ctry7_7);
        Ctry7_7.setTextContent("NL");
        
        //Tag Id
        Element Id_new7 = doc.createElement("Id");
        UltmtCdtr7.appendChild(Id_new7);
        Element OrgId7 = doc.createElement("OrgId");
        Id_new7.appendChild(OrgId7);
        Element AnyBIC7 = doc.createElement("AnyBIC");
        OrgId7.appendChild(AnyBIC7);
        AnyBIC7.setTextContent("RABONL7U");
        
        //Tag InstgAgt
        
        Element InstgAgt7 = doc.createElement("InstgAgt");
        DrctDbtTxInf7.appendChild(InstgAgt7);
        Element FinInstnId7_New = doc.createElement("FinInstnId");
        InstgAgt7.appendChild(FinInstnId7_New);
        Element BICFI7_7 = doc.createElement("BICFI");
        FinInstnId7_New.appendChild(BICFI7_7);
        BICFI7_7.setTextContent("RABONL7U");
        
        //Tag Dbtr
        Element Dbtr7_New = doc.createElement("Dbtr");
        DrctDbtTxInf7.appendChild(Dbtr7_New);
        Element Nm_7 = doc.createElement("Nm");
        Dbtr7_New.appendChild(Nm_7);
        Nm_7.setTextContent("D. Demir Halk Debtor");
        
        //Tag DbtrAcct
        
        Element DbtrAcct7 = doc.createElement("DbtrAcct");
        DrctDbtTxInf7.appendChild(DbtrAcct7);
        Element Id_7_New = doc.createElement("Id");
        DbtrAcct7.appendChild(Id_7_New);
        Element IBAN7 = doc.createElement("IBAN");
        Id_7_New.appendChild(IBAN7);
        IBAN7.setTextContent("NL87NNBA0779077877");
        
        //Tag DbtrAgt
        Element DbtrAgt7 = doc.createElement("DbtrAgt");
        DrctDbtTxInf7.appendChild(DbtrAgt7);
        Element FinInstnId7_7 = doc.createElement("FinInstnId");
        DbtrAgt7.appendChild(FinInstnId7_7);
        Element BICFI7_7_7 = doc.createElement("BICFI");
        FinInstnId7_7.appendChild(BICFI7_7_7);
        BICFI7_7_7.setTextContent("NNBANL7G");
        
        //Tag UltmtDbtr
        
        Element UltmtDbtr7 = doc.createElement("UltmtDbtr");
        DrctDbtTxInf7.appendChild(UltmtDbtr7);
        Element Nm7_7_7 = doc.createElement("Nm");
        UltmtDbtr7.appendChild(Nm7_7_7);
        Nm7_7_7.setTextContent("str1234");
        
        //Tag PstlAdr
        
        Element PstlAdr7_7_7 = doc.createElement("PstlAdr");
        UltmtDbtr7.appendChild(PstlAdr7_7_7);
        //PstlAdr7_7_7.setTextContent("str1234");
        
        Element Dept7_7 = doc.createElement("Dept");
        PstlAdr7_7_7.appendChild(Dept7_7);
        Dept7_7.setTextContent("str1234");
        
        Element SubDept7_7 = doc.createElement("SubDept");
        PstlAdr7_7_7.appendChild(SubDept7_7);
        SubDept7_7.setTextContent("str1234");
        
        Element StrtNm7_7 = doc.createElement("StrtNm");
        PstlAdr7_7_7.appendChild(StrtNm7_7);
        StrtNm7_7.setTextContent("str1234");
        
        Element BldgNb7_7 = doc.createElement("BldgNb");
        PstlAdr7_7_7.appendChild(BldgNb7_7);
        BldgNb7_7.setTextContent("str1234");
        
        Element PstCd7_7 = doc.createElement("PstCd");
        PstlAdr7_7_7.appendChild(PstCd7_7);
        PstCd7_7.setTextContent("str1234");
        
        Element TwnNm7_7 = doc.createElement("TwnNm");
        PstlAdr7_7_7.appendChild(TwnNm7_7);
        TwnNm7_7.setTextContent("str1234");
        
        Element CtrySubDvsn7_7 = doc.createElement("CtrySubDvsn");
        PstlAdr7_7_7.appendChild(CtrySubDvsn7_7);
        CtrySubDvsn7_7.setTextContent("str1234");
        
        Element Ctry77 = doc.createElement("Ctry");
        PstlAdr7_7_7.appendChild(Ctry77);
        Ctry77.setTextContent("str1234");
        
        Element Id_Inside7 = doc.createElement("Id");
        UltmtDbtr7.appendChild(Id_Inside7);
        
        
        
        //Tag OrgId
        Element OrgId7_7 = doc.createElement("OrgId");
        Id_Inside7.appendChild(OrgId7_7);
        Element AnyBIC7_7 = doc.createElement("AnyBIC");
        OrgId7_7.appendChild(AnyBIC7_7);
        AnyBIC7_7.setTextContent("RABONL7U");
        
        //RmtInf
        Element RmtInf7 = doc.createElement("RmtInf");
        DrctDbtTxInf7.appendChild(RmtInf7);
        Element Ustrd7 = doc.createElement("Ustrd");
        RmtInf7.appendChild(Ustrd7);
        Ustrd7.setTextContent("Direct Debit Collection");
        
        //Amount8

        
        Element DrctDbtTxInf8 = doc.createElement("DrctDbtTxInf");
        FIToFICstmrDrctDbt.appendChild(DrctDbtTxInf8);
        Element PmtId8 = doc.createElement("PmtId");
        DrctDbtTxInf8.appendChild(PmtId8);
        Element InstrId8 = doc.createElement("InstrId");
        PmtId8.appendChild(InstrId8);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf8 = doc.createElement("PmtTpInf");
        DrctDbtTxInf8.appendChild(PmtTpInf8);
        Element SvcLvl8 = doc.createElement("SvcLvl");
        PmtTpInf8.appendChild(SvcLvl8);
        Element Cd8 = doc.createElement("Cd");
        SvcLvl8.appendChild(Cd8);
        Cd8.setTextContent("SEPA");
        
        //tag LclInstrm
        
        //Element PmtTpInf8 = doc.createElement("PmtTpInf");
        //DrctDbtTxInf8.appendChild(PmtTpInf8);
        Element LclInstrm8 = doc.createElement("LclInstrm");
        PmtTpInf8.appendChild(LclInstrm8);
        Element Cd8_ = doc.createElement("Cd");
        LclInstrm8.appendChild(Cd8_);
        Cd8_.setTextContent("CORE");
        Element SeqTp8 = doc.createElement("SeqTp");
        PmtTpInf8.appendChild(SeqTp8);
        SeqTp8.setTextContent("RCUR");
        
        //tag CtgyPurp
        Element CtgyPurp8 = doc.createElement("CtgyPurp");
        PmtTpInf8.appendChild(CtgyPurp8);
        Element Cd8_8 = doc.createElement("Cd");
        CtgyPurp8.appendChild(Cd8_8);
        Cd8_8.setTextContent("str1");
        
        InstrId8.setTextContent(MsgIdText);
        Element EndToEndId8 = doc.createElement("EndToEndId");
        PmtId8.appendChild(EndToEndId8);
        //String EndToEndIdText = "E1E"+Messagetime111;
        EndToEndId8.setTextContent(EndToEndIdText);
        Element TxId8 = doc.createElement("TxId");
        PmtId8.appendChild(TxId8);
        TxId8.setTextContent("TXDIDI1A100811F"+Messagetime111);
        
        
        Element IntrBkSttlmAmt8 = doc.createElement("IntrBkSttlmAmt");
        DrctDbtTxInf8.appendChild(IntrBkSttlmAmt8);
        IntrBkSttlmAmt8.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt8.setTextContent("18");
        Element ChrgBr8 = doc.createElement("ChrgBr");
        DrctDbtTxInf8.appendChild(ChrgBr8);
        ChrgBr8.setTextContent("SLEV");
        
        //ReqdColltnDt
        Element ReqdColltnDt8 = doc.createElement("ReqdColltnDt");
        DrctDbtTxInf8.appendChild(ReqdColltnDt8);
        ReqdColltnDt8.setTextContent(formattedNextDay);
        
        
        Element DrctDbtTx8 = doc.createElement("DrctDbtTx");
        DrctDbtTxInf8.appendChild(DrctDbtTx8);
        Element MndtRltdInf8 = doc.createElement("MndtRltdInf");
        DrctDbtTx8.appendChild(MndtRltdInf8);
        Element MndtId8 = doc.createElement("MndtId");
        MndtRltdInf8.appendChild(MndtId8);
        MndtId8.setTextContent("88088");
        
        Element DtOfSgntr8 = doc.createElement("DtOfSgntr");
        MndtRltdInf8.appendChild(DtOfSgntr8);
        DtOfSgntr8.setTextContent(formattedNextDay);
        
        Element AmdmntInd8 = doc.createElement("AmdmntInd");
        MndtRltdInf8.appendChild(AmdmntInd8);
        AmdmntInd8.setTextContent("true");
        
        
        //AmdmntInfDtls
        Element AmdmntInfDtls8 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf8.appendChild(AmdmntInfDtls8);
        Element OrgnlCdtrSchmeId8 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls8.appendChild(OrgnlCdtrSchmeId8);
        Element Nm8 = doc.createElement("Nm");
        OrgnlCdtrSchmeId8.appendChild(Nm8);
        Nm8.setTextContent("Vodafone Co");
        
        Element Id8 = doc.createElement("Id");
        OrgnlCdtrSchmeId8.appendChild(Id8);
        //PrvtId
        
        Element PrvtId8 = doc.createElement("PrvtId");
        Id8.appendChild(PrvtId8);
        //Othr
        Element Othr8 = doc.createElement("Othr");
        PrvtId8.appendChild(Othr8);
        Element Id8_8 = doc.createElement("Id");
        Othr8.appendChild(Id8_8);
        Id8_8.setTextContent("NL88ZZZ880880080000");
        
       
        //SchmeNm
        Element SchmeNm8 = doc.createElement("SchmeNm");
        Othr8.appendChild(SchmeNm8);
        Element Prtry8 = doc.createElement("Prtry");
        SchmeNm8.appendChild(Prtry8);
        Prtry8.setTextContent("SEPA");
        
        
        //OrgnlDbtrAcct
        Element OrgnlDbtrAcct8 = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls8.appendChild(OrgnlDbtrAcct8);
        Element Id_8_8_8 = doc.createElement("Id");
        OrgnlDbtrAcct8.appendChild(Id_8_8_8);
        Element IBAN8__8 = doc.createElement("IBAN");
        Id_8_8_8.appendChild(IBAN8__8);
        IBAN8__8.setTextContent("NL88NNBA0889088888");
        
        //Tag ElctrncSgntr8
        Element ElctrncSgntr8 = doc.createElement("ElctrncSgntr");
        MndtRltdInf8.appendChild(ElctrncSgntr8);
        ElctrncSgntr8.setTextContent("DIANA");
        
        //Tag CdtrSchmeId
        Element CdtrSchmeId8 = doc.createElement("CdtrSchmeId");
        DrctDbtTx8.appendChild(CdtrSchmeId8);
        Element Id_New_8 = doc.createElement("Id");
        CdtrSchmeId8.appendChild(Id_New_8);
        Element PrvtId8_8 = doc.createElement("PrvtId");
        Id_New_8.appendChild(PrvtId8_8);
        Element Othr_8 = doc.createElement("Othr");
        PrvtId8_8.appendChild(Othr_8);
        Element Id_New_8_8 = doc.createElement("Id");
        Othr_8.appendChild(Id_New_8_8);
        Id_New_8_8.setTextContent("NL88ZZZ880880080000");
        
        //Tag SchmeNm
//        <SchmeNm>
//        <Prtry>SEPA</Prtry>
//        </SchmeNm>
        
        Element SchmeNm8_8 = doc.createElement("SchmeNm");
        Othr_8.appendChild(SchmeNm8_8);
        Element Prtry8_8 = doc.createElement("Prtry");
        SchmeNm8_8.appendChild(Prtry8_8);
        Prtry8_8.setTextContent("SEPA");
        
        
        //Tag Cdtr
        Element Cdtr8 = doc.createElement("Cdtr");
        DrctDbtTxInf8.appendChild(Cdtr8);
        Element Nm8_New = doc.createElement("Nm");
        Cdtr8.appendChild(Nm8_New);
        Nm8_New.setTextContent("Vodafon Co");
        
        //Tag PstlAdr
        Element PstlAdr8 = doc.createElement("PstlAdr");
        Cdtr8.appendChild(PstlAdr8);
        Element Ctry8 = doc.createElement("Ctry");
        PstlAdr8.appendChild(Ctry8);
        Ctry8.setTextContent("NL");
        //<AdrLine>s Gravenhof 2 D</AdrLine>
        //<AdrLine>8808 DN ZUTPHEN</AdrLine>
        Element AdrLine8 = doc.createElement("AdrLine");
        PstlAdr8.appendChild(AdrLine8);
        AdrLine8.setTextContent("s Gravenhof 2 D");
        Element AdrLine8_8 = doc.createElement("AdrLine");
        PstlAdr8.appendChild(AdrLine8_8);
        AdrLine8_8.setTextContent("8808 DN ZUTPHEN");
        
        
        
        //Tag CdtrAcct
        Element CdtrAcct8 = doc.createElement("CdtrAcct");
        DrctDbtTxInf8.appendChild(CdtrAcct8);
        Element Id_8_Acct = doc.createElement("Id");
        CdtrAcct8.appendChild(Id_8_Acct);
        Element IBAN_8 = doc.createElement("IBAN");
        Id_8_Acct.appendChild(IBAN_8);
        IBAN_8.setTextContent("NL88RABO0800008888");
        
        //Tag CdtrAgt
        Element CdtrAgt8 = doc.createElement("CdtrAgt");
        DrctDbtTxInf8.appendChild(CdtrAgt8);
        Element FinInstnId8 = doc.createElement("FinInstnId");
        CdtrAgt8.appendChild(FinInstnId8);
        Element BICFI8_New = doc.createElement("BICFI");
        FinInstnId8.appendChild(BICFI8_New);
        BICFI8_New.setTextContent("RABONL8U");
        
        //Tag UltmtCdtr
        Element UltmtCdtr8 = doc.createElement("UltmtCdtr");
        DrctDbtTxInf8.appendChild(UltmtCdtr8);
        Element Nm8_8 = doc.createElement("Nm");
        UltmtCdtr8.appendChild(Nm8_8);
        Nm8_8.setTextContent("str1234");
        
        //Tag PstlAdr
        Element PstlAdr8_8 = doc.createElement("PstlAdr");
        UltmtCdtr8.appendChild(PstlAdr8_8);
        Element Dept8 = doc.createElement("Dept");
        PstlAdr8_8.appendChild(Dept8);
        Dept8.setTextContent("str1234");
        
        //Tag SubDept
        Element SubDept8 = doc.createElement("SubDept");
        PstlAdr8_8.appendChild(SubDept8);
        SubDept8.setTextContent("str1234");
        
        Element StrtNm8 = doc.createElement("StrtNm");
        PstlAdr8_8.appendChild(StrtNm8);
        StrtNm8.setTextContent("str1234");
        
        Element BldgNb8 = doc.createElement("BldgNb");
        PstlAdr8_8.appendChild(BldgNb8);
        BldgNb8.setTextContent("str1234");
        
        Element PstCd8 = doc.createElement("PstCd");
        PstlAdr8_8.appendChild(PstCd8);
        PstCd8.setTextContent("str1234");
        
        Element TwnNm8 = doc.createElement("TwnNm");
        PstlAdr8_8.appendChild(TwnNm8);
        TwnNm8.setTextContent("str1234");
        
        Element CtrySubDvsn8 = doc.createElement("CtrySubDvsn");
        PstlAdr8_8.appendChild(CtrySubDvsn8);
        CtrySubDvsn8.setTextContent("str1234");
        
        
        Element Ctry8_8 = doc.createElement("Ctry");
        PstlAdr8_8.appendChild(Ctry8_8);
        Ctry8_8.setTextContent("NL");
        
        //Tag Id
        Element Id_new8 = doc.createElement("Id");
        UltmtCdtr8.appendChild(Id_new8);
        Element OrgId8 = doc.createElement("OrgId");
        Id_new8.appendChild(OrgId8);
        Element AnyBIC8 = doc.createElement("AnyBIC");
        OrgId8.appendChild(AnyBIC8);
        AnyBIC8.setTextContent("RABONL8U");
        
        //Tag InstgAgt
        
        Element InstgAgt8 = doc.createElement("InstgAgt");
        DrctDbtTxInf8.appendChild(InstgAgt8);
        Element FinInstnId8_New = doc.createElement("FinInstnId");
        InstgAgt8.appendChild(FinInstnId8_New);
        Element BICFI8_8 = doc.createElement("BICFI");
        FinInstnId8_New.appendChild(BICFI8_8);
        BICFI8_8.setTextContent("RABONL8U");
        
        //Tag Dbtr
        Element Dbtr8_New = doc.createElement("Dbtr");
        DrctDbtTxInf8.appendChild(Dbtr8_New);
        Element Nm_8 = doc.createElement("Nm");
        Dbtr8_New.appendChild(Nm_8);
        Nm_8.setTextContent("D. Demir Halk Debtor");
        
        //Tag DbtrAcct
        
        Element DbtrAcct8 = doc.createElement("DbtrAcct");
        DrctDbtTxInf8.appendChild(DbtrAcct8);
        Element Id_8_New = doc.createElement("Id");
        DbtrAcct8.appendChild(Id_8_New);
        Element IBAN8 = doc.createElement("IBAN");
        Id_8_New.appendChild(IBAN8);
        IBAN8.setTextContent("NL88NNBA0889088888");
        
        //Tag DbtrAgt
        Element DbtrAgt8 = doc.createElement("DbtrAgt");
        DrctDbtTxInf8.appendChild(DbtrAgt8);
        Element FinInstnId8_8 = doc.createElement("FinInstnId");
        DbtrAgt8.appendChild(FinInstnId8_8);
        Element BICFI8_8_8 = doc.createElement("BICFI");
        FinInstnId8_8.appendChild(BICFI8_8_8);
        BICFI8_8_8.setTextContent("NNBANL8G");
        
        //Tag UltmtDbtr
        
        Element UltmtDbtr8 = doc.createElement("UltmtDbtr");
        DrctDbtTxInf8.appendChild(UltmtDbtr8);
        Element Nm8_8_8 = doc.createElement("Nm");
        UltmtDbtr8.appendChild(Nm8_8_8);
        Nm8_8_8.setTextContent("str1234");
        
        //Tag PstlAdr
        
        Element PstlAdr8_8_8 = doc.createElement("PstlAdr");
        UltmtDbtr8.appendChild(PstlAdr8_8_8);
        //PstlAdr8_8_8.setTextContent("str1234");
        
        Element Dept8_8 = doc.createElement("Dept");
        PstlAdr8_8_8.appendChild(Dept8_8);
        Dept8_8.setTextContent("str1234");
        
        Element SubDept8_8 = doc.createElement("SubDept");
        PstlAdr8_8_8.appendChild(SubDept8_8);
        SubDept8_8.setTextContent("str1234");
        
        Element StrtNm8_8 = doc.createElement("StrtNm");
        PstlAdr8_8_8.appendChild(StrtNm8_8);
        StrtNm8_8.setTextContent("str1234");
        
        Element BldgNb8_8 = doc.createElement("BldgNb");
        PstlAdr8_8_8.appendChild(BldgNb8_8);
        BldgNb8_8.setTextContent("str1234");
        
        Element PstCd8_8 = doc.createElement("PstCd");
        PstlAdr8_8_8.appendChild(PstCd8_8);
        PstCd8_8.setTextContent("str1234");
        
        Element TwnNm8_8 = doc.createElement("TwnNm");
        PstlAdr8_8_8.appendChild(TwnNm8_8);
        TwnNm8_8.setTextContent("str1234");
        
        Element CtrySubDvsn8_8 = doc.createElement("CtrySubDvsn");
        PstlAdr8_8_8.appendChild(CtrySubDvsn8_8);
        CtrySubDvsn8_8.setTextContent("str1234");
        
        Element Ctry88 = doc.createElement("Ctry");
        PstlAdr8_8_8.appendChild(Ctry88);
        Ctry88.setTextContent("NL");
        
        Element Id_Inside8 = doc.createElement("Id");
        UltmtDbtr8.appendChild(Id_Inside8);
        
        
        
        //Tag OrgId
        Element OrgId8_8 = doc.createElement("OrgId");
        Id_Inside8.appendChild(OrgId8_8);
        Element AnyBIC8_8 = doc.createElement("AnyBIC");
        OrgId8_8.appendChild(AnyBIC8_8);
        AnyBIC8_8.setTextContent("RABONL8U");
        
        //RmtInf
        Element RmtInf8 = doc.createElement("RmtInf");
        DrctDbtTxInf8.appendChild(RmtInf8);
        Element Ustrd8 = doc.createElement("Ustrd");
        RmtInf8.appendChild(Ustrd8);
        Ustrd8.setTextContent("Direct Debit Collection");
        
        //Amount9

        
        Element DrctDbtTxInf9 = doc.createElement("DrctDbtTxInf");
        FIToFICstmrDrctDbt.appendChild(DrctDbtTxInf9);
        Element PmtId9 = doc.createElement("PmtId");
        DrctDbtTxInf9.appendChild(PmtId9);
        Element InstrId9 = doc.createElement("InstrId");
        PmtId9.appendChild(InstrId9);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf9 = doc.createElement("PmtTpInf");
        DrctDbtTxInf9.appendChild(PmtTpInf9);
        Element SvcLvl9 = doc.createElement("SvcLvl");
        PmtTpInf9.appendChild(SvcLvl9);
        Element Cd9 = doc.createElement("Cd");
        SvcLvl9.appendChild(Cd9);
        Cd9.setTextContent("SEPA");
        
        //tag LclInstrm
        
        //Element PmtTpInf9 = doc.createElement("PmtTpInf");
        //DrctDbtTxInf9.appendChild(PmtTpInf9);
        Element LclInstrm9 = doc.createElement("LclInstrm");
        PmtTpInf9.appendChild(LclInstrm9);
        Element Cd9_ = doc.createElement("Cd");
        LclInstrm9.appendChild(Cd9_);
        Cd9_.setTextContent("CORE");
        Element SeqTp9 = doc.createElement("SeqTp");
        PmtTpInf9.appendChild(SeqTp9);
        SeqTp9.setTextContent("RCUR");
        
        //tag CtgyPurp
        Element CtgyPurp9 = doc.createElement("CtgyPurp");
        PmtTpInf9.appendChild(CtgyPurp9);
        Element Cd9_9 = doc.createElement("Cd");
        CtgyPurp9.appendChild(Cd9_9);
        Cd9_9.setTextContent("str1");
        
        InstrId9.setTextContent(MsgIdText);
        Element EndToEndId9 = doc.createElement("EndToEndId");
        PmtId9.appendChild(EndToEndId9);
        //String EndToEndIdText = "E1E"+Messagetime111;
        EndToEndId9.setTextContent(EndToEndIdText);
        Element TxId9 = doc.createElement("TxId");
        PmtId9.appendChild(TxId9);
        TxId9.setTextContent("TXDIDI1A100911F"+Messagetime111);
        
        
        Element IntrBkSttlmAmt9 = doc.createElement("IntrBkSttlmAmt");
        DrctDbtTxInf9.appendChild(IntrBkSttlmAmt9);
        IntrBkSttlmAmt9.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt9.setTextContent("19");
        Element ChrgBr9 = doc.createElement("ChrgBr");
        DrctDbtTxInf9.appendChild(ChrgBr9);
        ChrgBr9.setTextContent("SLEV");
        
        //ReqdColltnDt
        Element ReqdColltnDt9 = doc.createElement("ReqdColltnDt");
        DrctDbtTxInf9.appendChild(ReqdColltnDt9);
        ReqdColltnDt9.setTextContent(formattedNextDay);
        
        
        Element DrctDbtTx9 = doc.createElement("DrctDbtTx");
        DrctDbtTxInf9.appendChild(DrctDbtTx9);
        Element MndtRltdInf9 = doc.createElement("MndtRltdInf");
        DrctDbtTx9.appendChild(MndtRltdInf9);
        Element MndtId9 = doc.createElement("MndtId");
        MndtRltdInf9.appendChild(MndtId9);
        MndtId9.setTextContent("41019");
        
        Element DtOfSgntr9 = doc.createElement("DtOfSgntr");
        MndtRltdInf9.appendChild(DtOfSgntr9);
        DtOfSgntr9.setTextContent(formattedNextDay);
        
        Element AmdmntInd9 = doc.createElement("AmdmntInd");
        MndtRltdInf9.appendChild(AmdmntInd9);
        AmdmntInd9.setTextContent("true");
        
        
        //AmdmntInfDtls
        Element AmdmntInfDtls9 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf9.appendChild(AmdmntInfDtls9);
        Element OrgnlCdtrSchmeId9 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls9.appendChild(OrgnlCdtrSchmeId9);
        Element Nm9 = doc.createElement("Nm");
        OrgnlCdtrSchmeId9.appendChild(Nm9);
        Nm9.setTextContent("Vodafone Co");
        
        Element Id9 = doc.createElement("Id");
        OrgnlCdtrSchmeId9.appendChild(Id9);
        //PrvtId
        
        Element PrvtId9 = doc.createElement("PrvtId");
        Id9.appendChild(PrvtId9);
        //Othr
        Element Othr9 = doc.createElement("Othr");
        PrvtId9.appendChild(Othr9);
        Element Id9_9 = doc.createElement("Id");
        Othr9.appendChild(Id9_9);
        Id9_9.setTextContent("NL99ZZZ990990090000");
        
       
        //SchmeNm
        Element SchmeNm9 = doc.createElement("SchmeNm");
        Othr9.appendChild(SchmeNm9);
        Element Prtry9 = doc.createElement("Prtry");
        SchmeNm9.appendChild(Prtry9);
        Prtry9.setTextContent("SEPA");
        
        
        //OrgnlDbtrAcct
        Element OrgnlDbtrAcct9 = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls9.appendChild(OrgnlDbtrAcct9);
        Element Id_9_9_9 = doc.createElement("Id");
        OrgnlDbtrAcct9.appendChild(Id_9_9_9);
        Element IBAN9__9 = doc.createElement("IBAN");
        Id_9_9_9.appendChild(IBAN9__9);
        IBAN9__9.setTextContent("NL99NNBA0999099999");
        
        //Tag ElctrncSgntr9
        Element ElctrncSgntr9 = doc.createElement("ElctrncSgntr");
        MndtRltdInf9.appendChild(ElctrncSgntr9);
        ElctrncSgntr9.setTextContent("DIANA");
        
        //Tag CdtrSchmeId
        Element CdtrSchmeId9 = doc.createElement("CdtrSchmeId");
        DrctDbtTx9.appendChild(CdtrSchmeId9);
        Element Id_New_9 = doc.createElement("Id");
        CdtrSchmeId9.appendChild(Id_New_9);
        Element PrvtId9_9 = doc.createElement("PrvtId");
        Id_New_9.appendChild(PrvtId9_9);
        Element Othr_9 = doc.createElement("Othr");
        PrvtId9_9.appendChild(Othr_9);
        Element Id_New_9_9 = doc.createElement("Id");
        Othr_9.appendChild(Id_New_9_9);
        Id_New_9_9.setTextContent("NL99ZZZ990990090000");
        
        //Tag SchmeNm
//        <SchmeNm>
//        <Prtry>SEPA</Prtry>
//        </SchmeNm>
        
        Element SchmeNm9_9 = doc.createElement("SchmeNm");
        Othr_9.appendChild(SchmeNm9_9);
        Element Prtry9_9 = doc.createElement("Prtry");
        SchmeNm9_9.appendChild(Prtry9_9);
        Prtry9_9.setTextContent("SEPA");
        
        
        //Tag Cdtr
        Element Cdtr9 = doc.createElement("Cdtr");
        DrctDbtTxInf9.appendChild(Cdtr9);
        Element Nm9_New = doc.createElement("Nm");
        Cdtr9.appendChild(Nm9_New);
        Nm9_New.setTextContent("Vodafon Co");
        
        //Tag PstlAdr
        Element PstlAdr9 = doc.createElement("PstlAdr");
        Cdtr9.appendChild(PstlAdr9);
        Element Ctry9 = doc.createElement("Ctry");
        PstlAdr9.appendChild(Ctry9);
        Ctry9.setTextContent("NL");
        //<AdrLine>s Gravenhof 2 D</AdrLine>
        //<AdrLine>9909 DN ZUTPHEN</AdrLine>
        Element AdrLine9 = doc.createElement("AdrLine");
        PstlAdr9.appendChild(AdrLine9);
        AdrLine9.setTextContent("s Gravenhof 2 D");
        Element AdrLine9_9 = doc.createElement("AdrLine");
        PstlAdr9.appendChild(AdrLine9_9);
        AdrLine9_9.setTextContent("9909 DN ZUTPHEN");
        
        
        
        //Tag CdtrAcct
        Element CdtrAcct9 = doc.createElement("CdtrAcct");
        DrctDbtTxInf9.appendChild(CdtrAcct9);
        Element Id_9_Acct = doc.createElement("Id");
        CdtrAcct9.appendChild(Id_9_Acct);
        Element IBAN_9 = doc.createElement("IBAN");
        Id_9_Acct.appendChild(IBAN_9);
        IBAN_9.setTextContent("NL99RABO0900009999");
        
        //Tag CdtrAgt
        Element CdtrAgt9 = doc.createElement("CdtrAgt");
        DrctDbtTxInf9.appendChild(CdtrAgt9);
        Element FinInstnId9 = doc.createElement("FinInstnId");
        CdtrAgt9.appendChild(FinInstnId9);
        Element BICFI9_New = doc.createElement("BICFI");
        FinInstnId9.appendChild(BICFI9_New);
        BICFI9_New.setTextContent("RABONL9U");
        
        //Tag UltmtCdtr
        Element UltmtCdtr9 = doc.createElement("UltmtCdtr");
        DrctDbtTxInf9.appendChild(UltmtCdtr9);
        Element Nm9_9 = doc.createElement("Nm");
        UltmtCdtr9.appendChild(Nm9_9);
        Nm9_9.setTextContent("str1234");
        
        //Tag PstlAdr
        Element PstlAdr9_9 = doc.createElement("PstlAdr");
        UltmtCdtr9.appendChild(PstlAdr9_9);
        Element Dept9 = doc.createElement("Dept");
        PstlAdr9_9.appendChild(Dept9);
        Dept9.setTextContent("str1234");
        
        //Tag SubDept
        Element SubDept9 = doc.createElement("SubDept");
        PstlAdr9_9.appendChild(SubDept9);
        SubDept9.setTextContent("str1234");
        
        Element StrtNm9 = doc.createElement("StrtNm");
        PstlAdr9_9.appendChild(StrtNm9);
        StrtNm9.setTextContent("str1234");
        
        Element BldgNb9 = doc.createElement("BldgNb");
        PstlAdr9_9.appendChild(BldgNb9);
        BldgNb9.setTextContent("str1234");
        
        Element PstCd9 = doc.createElement("PstCd");
        PstlAdr9_9.appendChild(PstCd9);
        PstCd9.setTextContent("str1234");
        
        Element TwnNm9 = doc.createElement("TwnNm");
        PstlAdr9_9.appendChild(TwnNm9);
        TwnNm9.setTextContent("str1234");
        
        Element CtrySubDvsn9 = doc.createElement("CtrySubDvsn");
        PstlAdr9_9.appendChild(CtrySubDvsn9);
        CtrySubDvsn9.setTextContent("str1234");
        
        
        Element Ctry9_9 = doc.createElement("Ctry");
        PstlAdr9_9.appendChild(Ctry9_9);
        Ctry9_9.setTextContent("NL");
        
        //Tag Id
        Element Id_new9 = doc.createElement("Id");
        UltmtCdtr9.appendChild(Id_new9);
        Element OrgId9 = doc.createElement("OrgId");
        Id_new9.appendChild(OrgId9);
        Element AnyBIC9 = doc.createElement("AnyBIC");
        OrgId9.appendChild(AnyBIC9);
        AnyBIC9.setTextContent("RABONL9U");
        
        //Tag InstgAgt
        
        Element InstgAgt9 = doc.createElement("InstgAgt");
        DrctDbtTxInf9.appendChild(InstgAgt9);
        Element FinInstnId9_New = doc.createElement("FinInstnId");
        InstgAgt9.appendChild(FinInstnId9_New);
        Element BICFI9_9 = doc.createElement("BICFI");
        FinInstnId9_New.appendChild(BICFI9_9);
        BICFI9_9.setTextContent("RABONL9U");
        
        //Tag Dbtr
        Element Dbtr9_New = doc.createElement("Dbtr");
        DrctDbtTxInf9.appendChild(Dbtr9_New);
        Element Nm_9 = doc.createElement("Nm");
        Dbtr9_New.appendChild(Nm_9);
        Nm_9.setTextContent("D. Demir Halk Debtor");
        
        //Tag DbtrAcct
        
        Element DbtrAcct9 = doc.createElement("DbtrAcct");
        DrctDbtTxInf9.appendChild(DbtrAcct9);
        Element Id_9_New = doc.createElement("Id");
        DbtrAcct9.appendChild(Id_9_New);
        Element IBAN9 = doc.createElement("IBAN");
        Id_9_New.appendChild(IBAN9);
        IBAN9.setTextContent("NL99NNBA0999099999");
        
        //Tag DbtrAgt
        Element DbtrAgt9 = doc.createElement("DbtrAgt");
        DrctDbtTxInf9.appendChild(DbtrAgt9);
        Element FinInstnId9_9 = doc.createElement("FinInstnId");
        DbtrAgt9.appendChild(FinInstnId9_9);
        Element BICFI9_9_9 = doc.createElement("BICFI");
        FinInstnId9_9.appendChild(BICFI9_9_9);
        BICFI9_9_9.setTextContent("NNBANL9G");
        
        //Tag UltmtDbtr
        
        Element UltmtDbtr9 = doc.createElement("UltmtDbtr");
        DrctDbtTxInf9.appendChild(UltmtDbtr9);
        Element Nm9_9_9 = doc.createElement("Nm");
        UltmtDbtr9.appendChild(Nm9_9_9);
        Nm9_9_9.setTextContent("str1234");
        
        //Tag PstlAdr
        
        Element PstlAdr9_9_9 = doc.createElement("PstlAdr");
        UltmtDbtr9.appendChild(PstlAdr9_9_9);
        //PstlAdr9_9_9.setTextContent("str1234");
        
        Element Dept9_9 = doc.createElement("Dept");
        PstlAdr9_9_9.appendChild(Dept9_9);
        Dept9_9.setTextContent("str1234");
        
        Element SubDept9_9 = doc.createElement("SubDept");
        PstlAdr9_9_9.appendChild(SubDept9_9);
        SubDept9_9.setTextContent("str1234");
        
        Element StrtNm9_9 = doc.createElement("StrtNm");
        PstlAdr9_9_9.appendChild(StrtNm9_9);
        StrtNm9_9.setTextContent("str1234");
        
        Element BldgNb9_9 = doc.createElement("BldgNb");
        PstlAdr9_9_9.appendChild(BldgNb9_9);
        BldgNb9_9.setTextContent("str1234");
        
        Element PstCd9_9 = doc.createElement("PstCd");
        PstlAdr9_9_9.appendChild(PstCd9_9);
        PstCd9_9.setTextContent("str1234");
        
        Element TwnNm9_9 = doc.createElement("TwnNm");
        PstlAdr9_9_9.appendChild(TwnNm9_9);
        TwnNm9_9.setTextContent("str1234");
        
        Element CtrySubDvsn9_9 = doc.createElement("CtrySubDvsn");
        PstlAdr9_9_9.appendChild(CtrySubDvsn9_9);
        CtrySubDvsn9_9.setTextContent("str1234");
        
        Element Ctry99 = doc.createElement("Ctry");
        PstlAdr9_9_9.appendChild(Ctry99);
        Ctry99.setTextContent("str1234");
        
        Element Id_Inside9 = doc.createElement("Id");
        UltmtDbtr9.appendChild(Id_Inside9);
        
        
        
        //Tag OrgId
        Element OrgId9_9 = doc.createElement("OrgId");
        Id_Inside9.appendChild(OrgId9_9);
        Element AnyBIC9_9 = doc.createElement("AnyBIC");
        OrgId9_9.appendChild(AnyBIC9_9);
        AnyBIC9_9.setTextContent("RABONL9U");
        
        //RmtInf
        Element RmtInf9 = doc.createElement("RmtInf");
        DrctDbtTxInf9.appendChild(RmtInf9);
        Element Ustrd9 = doc.createElement("Ustrd");
        RmtInf9.appendChild(Ustrd9);
        Ustrd9.setTextContent("Direct Debit Collection");
        
        //Amount 10
        
        Element DrctDbtTxInf10 = doc.createElement("DrctDbtTxInf");
        FIToFICstmrDrctDbt.appendChild(DrctDbtTxInf10);
        Element PmtId10 = doc.createElement("PmtId");
        DrctDbtTxInf10.appendChild(PmtId10);
        Element InstrId10 = doc.createElement("InstrId");
        PmtId10.appendChild(InstrId10);
        
        //Create Element for PmtTpInf
        
        
        Element PmtTpInf10 = doc.createElement("PmtTpInf");
        DrctDbtTxInf10.appendChild(PmtTpInf10);
        Element SvcLvl10 = doc.createElement("SvcLvl");
        PmtTpInf10.appendChild(SvcLvl10);
        Element Cd10 = doc.createElement("Cd");
        SvcLvl10.appendChild(Cd10);
        Cd10.setTextContent("SEPA");
        
        //tag LclInstrm
        
        //Element PmtTpInf10 = doc.createElement("PmtTpInf");
        //DrctDbtTxInf10.appendChild(PmtTpInf10);
        Element LclInstrm10 = doc.createElement("LclInstrm");
        PmtTpInf10.appendChild(LclInstrm10);
        Element Cd10_ = doc.createElement("Cd");
        LclInstrm10.appendChild(Cd10_);
        Cd10_.setTextContent("CORE");
        Element SeqTp10 = doc.createElement("SeqTp");
        PmtTpInf10.appendChild(SeqTp10);
        SeqTp10.setTextContent("RCUR");
        
        //tag CtgyPurp
        Element CtgyPurp10 = doc.createElement("CtgyPurp");
        PmtTpInf10.appendChild(CtgyPurp10);
        Element Cd10_10 = doc.createElement("Cd");
        CtgyPurp10.appendChild(Cd10_10);
        Cd10_10.setTextContent("str1");
        
        InstrId10.setTextContent(MsgIdText);
        Element EndToEndId10 = doc.createElement("EndToEndId");
        PmtId10.appendChild(EndToEndId10);
        //String EndToEndIdText = "E1E"+Messagetime111;
        EndToEndId10.setTextContent(EndToEndIdText);
        Element TxId10 = doc.createElement("TxId");
        PmtId10.appendChild(TxId10);
        TxId10.setTextContent("TXDIDI1A1001011F"+Messagetime111);
        
        
        Element IntrBkSttlmAmt10 = doc.createElement("IntrBkSttlmAmt");
        DrctDbtTxInf10.appendChild(IntrBkSttlmAmt10);
        IntrBkSttlmAmt10.setAttribute("Ccy" , "EUR");
        IntrBkSttlmAmt10.setTextContent("20");
        Element ChrgBr10 = doc.createElement("ChrgBr");
        DrctDbtTxInf10.appendChild(ChrgBr10);
        ChrgBr10.setTextContent("SLEV");
        
        //ReqdColltnDt
        Element ReqdColltnDt10 = doc.createElement("ReqdColltnDt");
        DrctDbtTxInf10.appendChild(ReqdColltnDt10);
        ReqdColltnDt10.setTextContent(formattedNextDay);
        
        
        Element DrctDbtTx10 = doc.createElement("DrctDbtTx");
        DrctDbtTxInf10.appendChild(DrctDbtTx10);
        Element MndtRltdInf10 = doc.createElement("MndtRltdInf");
        DrctDbtTx10.appendChild(MndtRltdInf10);
        Element MndtId10 = doc.createElement("MndtId");
        MndtRltdInf10.appendChild(MndtId10);
        MndtId10.setTextContent("41014");
        
        Element DtOfSgntr10 = doc.createElement("DtOfSgntr");
        MndtRltdInf10.appendChild(DtOfSgntr10);
        DtOfSgntr10.setTextContent(formattedNextDay);
        
        Element AmdmntInd10 = doc.createElement("AmdmntInd");
        MndtRltdInf10.appendChild(AmdmntInd10);
        AmdmntInd10.setTextContent("true");
        
        
        //AmdmntInfDtls
        Element AmdmntInfDtls10 = doc.createElement("AmdmntInfDtls");
        MndtRltdInf10.appendChild(AmdmntInfDtls10);
        Element OrgnlCdtrSchmeId10 = doc.createElement("OrgnlCdtrSchmeId");
        AmdmntInfDtls10.appendChild(OrgnlCdtrSchmeId10);
        Element Nm10 = doc.createElement("Nm");
        OrgnlCdtrSchmeId10.appendChild(Nm10);
        Nm10.setTextContent("Vodafone Co");
        
        Element Id10 = doc.createElement("Id");
        OrgnlCdtrSchmeId10.appendChild(Id10);
        //PrvtId
        
        Element PrvtId10 = doc.createElement("PrvtId");
        Id10.appendChild(PrvtId10);
        //Othr
        Element Othr10 = doc.createElement("Othr");
        PrvtId10.appendChild(Othr10);
        Element Id10_10 = doc.createElement("Id");
        Othr10.appendChild(Id10_10);
        Id10_10.setTextContent("NL16ZZZ320750020000");
        
       
        //SchmeNm
        Element SchmeNm10 = doc.createElement("SchmeNm");
        Othr10.appendChild(SchmeNm10);
        Element Prtry10 = doc.createElement("Prtry");
        SchmeNm10.appendChild(Prtry10);
        Prtry10.setTextContent("SEPA");
        
        
        //OrgnlDbtrAcct
        Element OrgnlDbtrAcct10 = doc.createElement("OrgnlDbtrAcct");
        AmdmntInfDtls10.appendChild(OrgnlDbtrAcct10);
        Element Id_10_10_10 = doc.createElement("Id");
        OrgnlDbtrAcct10.appendChild(Id_10_10_10);
        Element IBAN10__10 = doc.createElement("IBAN");
        Id_10_10_10.appendChild(IBAN10__10);
        IBAN10__10.setTextContent("NL21NNBA1000000567");
        
        //Tag ElctrncSgntr10
        Element ElctrncSgntr10 = doc.createElement("ElctrncSgntr");
        MndtRltdInf10.appendChild(ElctrncSgntr10);
        ElctrncSgntr10.setTextContent("DIANA");
        
        //Tag CdtrSchmeId
        Element CdtrSchmeId10 = doc.createElement("CdtrSchmeId");
        DrctDbtTx10.appendChild(CdtrSchmeId10);
        Element Id_New_10 = doc.createElement("Id");
        CdtrSchmeId10.appendChild(Id_New_10);
        Element PrvtId10_10 = doc.createElement("PrvtId");
        Id_New_10.appendChild(PrvtId10_10);
        Element Othr_10 = doc.createElement("Othr");
        PrvtId10_10.appendChild(Othr_10);
        Element Id_New_10_10 = doc.createElement("Id");
        Othr_10.appendChild(Id_New_10_10);
        Id_New_10_10.setTextContent("NL16ZZZ320750020000");
        
        //Tag SchmeNm
//        <SchmeNm>
//        <Prtry>SEPA</Prtry>
//        </SchmeNm>
        
        Element SchmeNm10_10 = doc.createElement("SchmeNm");
        Othr_10.appendChild(SchmeNm10_10);
        Element Prtry10_10 = doc.createElement("Prtry");
        SchmeNm10_10.appendChild(Prtry10_10);
        Prtry10_10.setTextContent("SEPA");
        
        
        //Tag Cdtr
        Element Cdtr10 = doc.createElement("Cdtr");
        DrctDbtTxInf10.appendChild(Cdtr10);
        Element Nm10_New = doc.createElement("Nm");
        Cdtr10.appendChild(Nm10_New);
        Nm10_New.setTextContent("Vodafon Co");
        
        //Tag PstlAdr
        Element PstlAdr10 = doc.createElement("PstlAdr");
        Cdtr10.appendChild(PstlAdr10);
        Element Ctry10 = doc.createElement("Ctry");
        PstlAdr10.appendChild(Ctry10);
        Ctry10.setTextContent("NL");
        //<AdrLine>s Gravenhof 2 D</AdrLine>
        //<AdrLine>1010010 DN ZUTPHEN</AdrLine>
        Element AdrLine10 = doc.createElement("AdrLine");
        PstlAdr10.appendChild(AdrLine10);
        AdrLine10.setTextContent("s Gravenhof 2 D");
        Element AdrLine10_10 = doc.createElement("AdrLine");
        PstlAdr10.appendChild(AdrLine10_10);
        AdrLine10_10.setTextContent("7201 DN ZUTPHEN");
        
        
        
        //Tag CdtrAcct
        Element CdtrAcct10 = doc.createElement("CdtrAcct");
        DrctDbtTxInf10.appendChild(CdtrAcct10);
        Element Id_10_Acct = doc.createElement("Id");
        CdtrAcct10.appendChild(Id_10_Acct);
        Element IBAN_10 = doc.createElement("IBAN");
        Id_10_Acct.appendChild(IBAN_10);
        IBAN_10.setTextContent("NL1010RABO010000010101010");
        
        //Tag CdtrAgt
        Element CdtrAgt10 = doc.createElement("CdtrAgt");
        DrctDbtTxInf10.appendChild(CdtrAgt10);
        Element FinInstnId10 = doc.createElement("FinInstnId");
        CdtrAgt10.appendChild(FinInstnId10);
        Element BICFI10_New = doc.createElement("BICFI");
        FinInstnId10.appendChild(BICFI10_New);
        BICFI10_New.setTextContent("ABNANL2A");
        
        //Tag UltmtCdtr
        Element UltmtCdtr10 = doc.createElement("UltmtCdtr");
        DrctDbtTxInf10.appendChild(UltmtCdtr10);
        Element Nm10_10 = doc.createElement("Nm");
        UltmtCdtr10.appendChild(Nm10_10);
        Nm10_10.setTextContent("str1234");
        
        //Tag PstlAdr
        Element PstlAdr10_10 = doc.createElement("PstlAdr");
        UltmtCdtr10.appendChild(PstlAdr10_10);
        Element Dept10 = doc.createElement("Dept");
        PstlAdr10_10.appendChild(Dept10);
        Dept10.setTextContent("str1234");
        
        //Tag SubDept
        Element SubDept10 = doc.createElement("SubDept");
        PstlAdr10_10.appendChild(SubDept10);
        SubDept10.setTextContent("str1234");
        
        Element StrtNm10 = doc.createElement("StrtNm");
        PstlAdr10_10.appendChild(StrtNm10);
        StrtNm10.setTextContent("str1234");
        
        Element BldgNb10 = doc.createElement("BldgNb");
        PstlAdr10_10.appendChild(BldgNb10);
        BldgNb10.setTextContent("str1234");
        
        Element PstCd10 = doc.createElement("PstCd");
        PstlAdr10_10.appendChild(PstCd10);
        PstCd10.setTextContent("str1234");
        
        Element TwnNm10 = doc.createElement("TwnNm");
        PstlAdr10_10.appendChild(TwnNm10);
        TwnNm10.setTextContent("str1234");
        
        Element CtrySubDvsn10 = doc.createElement("CtrySubDvsn");
        PstlAdr10_10.appendChild(CtrySubDvsn10);
        CtrySubDvsn10.setTextContent("str1234");
        
        
        Element Ctry10_10 = doc.createElement("Ctry");
        PstlAdr10_10.appendChild(Ctry10_10);
        Ctry10_10.setTextContent("NL");
        
        //Tag Id
        Element Id_new10 = doc.createElement("Id");
        UltmtCdtr10.appendChild(Id_new10);
        Element OrgId10 = doc.createElement("OrgId");
        Id_new10.appendChild(OrgId10);
        Element AnyBIC10 = doc.createElement("AnyBIC");
        OrgId10.appendChild(AnyBIC10);
        AnyBIC10.setTextContent("ABNANL2A");
        
        //Tag InstgAgt
        
        Element InstgAgt10 = doc.createElement("InstgAgt");
        DrctDbtTxInf10.appendChild(InstgAgt10);
        Element FinInstnId10_New = doc.createElement("FinInstnId");
        InstgAgt10.appendChild(FinInstnId10_New);
        Element BICFI10_10 = doc.createElement("BICFI");
        FinInstnId10_New.appendChild(BICFI10_10);
        BICFI10_10.setTextContent("NNBANL2G");
        
        //Tag Dbtr
        Element Dbtr10_New = doc.createElement("Dbtr");
        DrctDbtTxInf10.appendChild(Dbtr10_New);
        Element Nm_10 = doc.createElement("Nm");
        Dbtr10_New.appendChild(Nm_10);
        Nm_10.setTextContent("D. Demir Halk Debtor");
        
        //Tag DbtrAcct
        
        Element DbtrAcct10 = doc.createElement("DbtrAcct");
        DrctDbtTxInf10.appendChild(DbtrAcct10);
        Element Id_10_New = doc.createElement("Id");
        DbtrAcct10.appendChild(Id_10_New);
        Element IBAN10 = doc.createElement("IBAN");
        Id_10_New.appendChild(IBAN10);
        IBAN10.setTextContent("NL1010NNBA010101001010101010");
        
        //Tag DbtrAgt
        Element DbtrAgt10 = doc.createElement("DbtrAgt");
        DrctDbtTxInf10.appendChild(DbtrAgt10);
        Element FinInstnId10_10 = doc.createElement("FinInstnId");
        DbtrAgt10.appendChild(FinInstnId10_10);
        Element BICFI10_10_10 = doc.createElement("BICFI");
        FinInstnId10_10.appendChild(BICFI10_10_10);
        BICFI10_10_10.setTextContent("NNBANL10G");
        
        //Tag UltmtDbtr
        
        Element UltmtDbtr10 = doc.createElement("UltmtDbtr");
        DrctDbtTxInf10.appendChild(UltmtDbtr10);
        Element Nm10_10_10 = doc.createElement("Nm");
        UltmtDbtr10.appendChild(Nm10_10_10);
        Nm10_10_10.setTextContent("str1234");
        
        //Tag PstlAdr
        
        Element PstlAdr10_10_10 = doc.createElement("PstlAdr");
        UltmtDbtr10.appendChild(PstlAdr10_10_10);
        //PstlAdr10_10_10.setTextContent("str1234");
        
        Element Dept10_10 = doc.createElement("Dept");
        PstlAdr10_10_10.appendChild(Dept10_10);
        Dept10_10.setTextContent("str1234");
        
        Element SubDept10_10 = doc.createElement("SubDept");
        PstlAdr10_10_10.appendChild(SubDept10_10);
        SubDept10_10.setTextContent("str1234");
        
        Element StrtNm10_10 = doc.createElement("StrtNm");
        PstlAdr10_10_10.appendChild(StrtNm10_10);
        StrtNm10_10.setTextContent("str1234");
        
        Element BldgNb10_10 = doc.createElement("BldgNb");
        PstlAdr10_10_10.appendChild(BldgNb10_10);
        BldgNb10_10.setTextContent("str1234");
        
        Element PstCd10_10 = doc.createElement("PstCd");
        PstlAdr10_10_10.appendChild(PstCd10_10);
        PstCd10_10.setTextContent("str1234");
        
        Element TwnNm10_10 = doc.createElement("TwnNm");
        PstlAdr10_10_10.appendChild(TwnNm10_10);
        TwnNm10_10.setTextContent("str1234");
        
        Element CtrySubDvsn10_10 = doc.createElement("CtrySubDvsn");
        PstlAdr10_10_10.appendChild(CtrySubDvsn10_10);
        CtrySubDvsn10_10.setTextContent("str1234");
        
        Element Ctry1010 = doc.createElement("Ctry");
        PstlAdr10_10_10.appendChild(Ctry1010);
        Ctry1010.setTextContent("NL");
        
        Element Id_Inside10 = doc.createElement("Id");
        UltmtDbtr10.appendChild(Id_Inside10);
        
        
        
        //Tag OrgId
        Element OrgId10_10 = doc.createElement("OrgId");
        Id_Inside10.appendChild(OrgId10_10);
        Element AnyBIC10_10 = doc.createElement("AnyBIC");
        OrgId10_10.appendChild(AnyBIC10_10);
        AnyBIC10_10.setTextContent("NNBANL2G");
        
        //RmtInf
        Element RmtInf10 = doc.createElement("RmtInf");
        DrctDbtTxInf10.appendChild(RmtInf10);
        Element Ustrd10 = doc.createElement("Ustrd");
        RmtInf10.appendChild(Ustrd10);
        Ustrd10.setTextContent("Direct Debit Collection incoming");
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("file.xml"));
        transformer.transform(source, result);


    }



}



