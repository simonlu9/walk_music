/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : cloud_music

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2016-07-14 18:35:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) NOT NULL,
  `pic` varchar(100) NOT NULL,
  `publish_date` date NOT NULL,
  `company` varchar(20) NOT NULL DEFAULT '',
  `comment_num` int(10) unsigned NOT NULL DEFAULT '0',
  `share_num` int(10) unsigned NOT NULL DEFAULT '0',
  `track_num` int(10) unsigned NOT NULL DEFAULT '0',
  `artist_id` int(10) unsigned NOT NULL DEFAULT '0',
  `desc` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------

-- ----------------------------
-- Table structure for artist
-- ----------------------------
DROP TABLE IF EXISTS `artist`;
CREATE TABLE `artist` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) NOT NULL DEFAULT '',
  `avatar` varchar(100) NOT NULL DEFAULT '',
  `desc` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of artist
-- ----------------------------

-- ----------------------------
-- Table structure for playlist
-- ----------------------------
DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist` (
  `id` int(10) unsigned NOT NULL,
  `uid` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(20) NOT NULL,
  `pic` varchar(100) NOT NULL,
  `publish_date` date NOT NULL,
  `comment_num` int(10) unsigned NOT NULL DEFAULT '0',
  `share_num` int(10) unsigned NOT NULL DEFAULT '0',
  `fav_num` int(10) unsigned NOT NULL DEFAULT '0',
  `track_num` int(10) unsigned NOT NULL DEFAULT '0',
  `play_num` int(10) unsigned NOT NULL DEFAULT '0',
  `desc` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of playlist
-- ----------------------------

-- ----------------------------
-- Table structure for track
-- ----------------------------
DROP TABLE IF EXISTS `track`;
CREATE TABLE `track` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(20) NOT NULL,
  `artist_id` int(10) unsigned NOT NULL DEFAULT '0',
  `album_id` int(10) unsigned NOT NULL DEFAULT '0',
  `duration` smallint(5) unsigned NOT NULL DEFAULT '0',
  `score` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `mv_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of track
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(10) unsigned NOT NULL,
  `level` tinyint(3) unsigned NOT NULL,
  `fans_num` int(10) unsigned NOT NULL,
  `feed_num` int(10) unsigned NOT NULL,
  `follow_num` int(10) unsigned NOT NULL,
  `age` smallint(5) unsigned NOT NULL,
  `gender` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `weibo_id` bigint(10) unsigned NOT NULL,
  `area` varchar(20) NOT NULL DEFAULT '',
  `sign` varchar(255) NOT NULL,
  `record` int(11) NOT NULL,
  `avatar` varchar(100) NOT NULL,
  `province` int(10) unsigned NOT NULL DEFAULT '0',
  `city` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
