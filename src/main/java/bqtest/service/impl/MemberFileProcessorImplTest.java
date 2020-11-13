package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberImporter;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MemberFileProcessorImplTest {

    public MemberFileProcessorImpl memberFileProcessorImpl;
    public MemberImporter memberImporter;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        memberFileProcessorImpl = new MemberFileProcessorImpl();
        memberImporter = new MemberImporterImpl();

    }

    @org.junit.jupiter.api.Test
    void getMemberImporter() throws Exception {
        System.out.println("Test case 1:");
        List<Member> members = memberImporter.importMembers(new File("Members.txt"));
        String expected = members.get(0).getFirstName();
        assertEquals(expected,"CAROLA");

    }

    @org.junit.jupiter.api.Test
    void getNonDuplicateMembers() throws Exception {
        System.out.println("Test case 2: ");
        List<Member> members = memberImporter.importMembers(new File("Members.txt"));
        System.out.println("File with duplication = " +members.size());
        int expected = members.size();
        List<Member> nonDuplicateMembers = memberFileProcessorImpl.getNonDuplicateMembers(members);
        System.out.println("File with No duplication = " +nonDuplicateMembers.size());
        int actual = nonDuplicateMembers.size();
        assertNotEquals(expected,actual);
    }

    @org.junit.jupiter.api.Test
    void splitMembersByState() throws Exception {
        System.out.println("Test Case 3: ");
        List<Member> members = memberImporter.importMembers(new File("Members.txt"));
        Map<String, List<Member>> stringListMap = memberFileProcessorImpl.splitMembersByState(members);
        List<Member> blazer_blvd = stringListMap.get("IL");
        int expected = blazer_blvd.size();
        System.out.println("There are Total 7 IL state in Member.txt file");
        assertEquals(expected,7);
    }
}