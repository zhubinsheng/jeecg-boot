package org.jeecg.modules.block.service.impl;

import org.jeecg.modules.block.entity.Block;
import org.jeecg.modules.block.mapper.BlockMapper;
import org.jeecg.modules.block.service.IBlockService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 楼栋表
 * @Author: jeecg-boot
 * @Date:   2021-04-25
 * @Version: V1.0
 */
@Service
public class BlockServiceImpl extends ServiceImpl<BlockMapper, Block> implements IBlockService {

}
