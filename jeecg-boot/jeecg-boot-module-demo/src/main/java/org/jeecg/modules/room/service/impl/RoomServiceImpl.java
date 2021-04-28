package org.jeecg.modules.room.service.impl;


import org.jeecg.modules.room.entity.Room;
import org.jeecg.modules.room.mapper.RoomMapper;
import org.jeecg.modules.room.service.IRoomService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 房间表
 * @Author: jeecg-boot
 * @Date:   2021-04-25
 * @Version: V1.0
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

}
