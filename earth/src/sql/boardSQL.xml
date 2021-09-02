<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> 
<mapper namespace="board">

	<select id="countAllNotice" resultType="int">
		select count(*) from notice
	</select>
	<select id="countAllFree" resultType="int">
		select count(*) from free
	</select>
	<select id="countAllDiary" resultType="int">
		select count(*) from diary
	</select>
	<select id="countAllChallenge" resultType="int">
		select count(*) from challenge
	</select>
	<select id="countAllToday" resultType="int">
		select count(*) from today
	</select>
	<select id="countAllShop" resultType="int">
		select count(*) from shop
	</select>
	<select id="countAllEvent" resultType="int">
		select count(*) from event
	</select>
	<select id="countAllTip" resultType="int">
		select count(*) from tip
	</select>
	
	<select id="getTodayArticles" parameterType="hashmap" resultType="todayDTO">		
		<![CDATA[
			select B.* from (select A.*, rownum r from (select * from today order by reg desc) A) B where r >= #{start} and r <= #{end}
		]]>
	</select>

	<insert id="upload" parameterType="todayDTO">
		insert into today values(today_seq.nextval, #{id}, #{ctt}, sysdate, #{code}, #{condition}) 
	</insert>
	
</mapper>
