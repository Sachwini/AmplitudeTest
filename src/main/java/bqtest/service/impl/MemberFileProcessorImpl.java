package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberFileProcessor;
import bqtest.service.MemberImporter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberFileProcessorImpl extends MemberFileProcessor {

    /*
     * Implement methods here
     */

    @Override
    protected MemberImporter getMemberImporter() {
        MemberImporter memberImporter = new MemberImporterImpl();
        return memberImporter;
    }

    @Override
    protected List<Member> getNonDuplicateMembers(List<Member> membersFromFile) {
        Set<Member> set = new LinkedHashSet<>();
        set.addAll(membersFromFile);
        membersFromFile.clear();
        membersFromFile.addAll(set);
        return membersFromFile;
    }

    @Override
    protected Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
        Map<String ,List<Member>> map= new HashMap<>();

        Set<String> states = new HashSet<>();

        for(Member member: validMembers){
            List<Member> m = new ArrayList<>();

            if(map.keySet().size()==0){
                map.put(member.getState(),Arrays.asList(member));
                states.add(member.getState());
                continue;
            }

            if(states.contains(member.getState()) ){

                m.addAll(map.get(member.getState()));
                m.add(member);
                map.put(member.getState(),m);

            }else{
                map.put(member.getState(), Arrays.asList(member));
            }
            states.add(member.getState());
        }
        return map;
    }

}
