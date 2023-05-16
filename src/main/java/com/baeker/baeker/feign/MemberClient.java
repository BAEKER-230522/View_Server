package com.baeker.baeker.feign;

import com.baeker.baeker.base.request.RsData;
import com.baeker.baeker.feign.dto.MemberJoinDto;
import com.baeker.baeker.msaController.dto.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "member", url = "http://localhost:8088/member")
@Qualifier
public interface MemberClient {

    @GetMapping("/get_member")
    RsData<Member> findByUsername(@SpringQueryMap Map<String, Object> parm);

    @PostMapping("/create")
    RsData<Member> join(@RequestBody MemberJoinDto dto);
}
