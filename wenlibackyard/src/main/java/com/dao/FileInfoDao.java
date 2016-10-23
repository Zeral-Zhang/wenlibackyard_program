package com.dao;

import org.springframework.stereotype.Repository;

import com.po.FileInfo;

@Repository("FileInfoDao")
public class FileInfoDao extends BaseDAO<FileInfo, String> {
}