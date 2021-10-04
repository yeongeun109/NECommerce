//package com.ecommerce.blockchain.domain.nft;
//
//import com.ecommerce.blockchain.domain.BaseTime;
//import com.ecommerce.blockchain.domain.user.User;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//
//@Getter
//@NoArgsConstructor
//@Entity
//public class NFT extends BaseTime {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JsonManagedReference
//    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    @Column(name = "seller_id",nullable = false)
//    @JoinColumn(name="user_id")
//    private User user;
//
//    @Column(nullable = false, columnDefinition = "LONGTEXT")
//    private String imageUrl;
//
//    @Column(nullable = false)
//    private String title;
//
//    @Column(nullable = true)
//    private String category;
//
//    @Column(nullable = true)
//    private String explanation;
//}
