package com.apartment.apart.community;

import com.apartment.apart.communityReply.CommunityReply;
import com.apartment.apart.communityReply.CommunityReplyForm;
import com.apartment.apart.communityReply.CommunityReplyService;
import com.apartment.apart.user.SiteUser;
import com.apartment.apart.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;

import java.security.Principal;

@RequestMapping("/community")
@RequiredArgsConstructor
@Controller
public class CommunityController {


}

