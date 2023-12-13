package com.apartment.apart.community;

import com.apartment.apart.DataNotException;
import com.apartment.apart.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    public List<Community> getList() {
        return this.communityRepository.findAll();
    }

    public Community getCommunity(Integer id) {
        Optional<Community> oc = this.communityRepository.findById(id);

        if (!oc.isPresent()) {
            throw new DataNotException("community not found");
        }

        return oc.get();
    }

    public Community create(String title, String content, SiteUser user) {
        Community community = new Community();
        community.setTitle(title);
        community.setContent(content);
        community.setCreateDate(LocalDateTime.now());
        community.setUser(user);

        this.communityRepository.save(community);
        return community;
    }

    public void modify(Community community, String title, String content) {
        community.setTitle(title);
        community.setContent(content);

        community.setModifyDate(LocalDateTime.now());
        this.communityRepository.save(community);
    }

    public Page<Community> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.communityRepository.findAll(pageable);
    }

    public void delete(Community community) {
        this.communityRepository.delete(community);
    }
}


