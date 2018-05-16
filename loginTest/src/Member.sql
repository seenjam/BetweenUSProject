-- ������ ȸ��
create table Member(
memID varchar2(30) primary key
,memPW varchar2(30)
,memName varchar2(30)
,memAddress varchar2(100)
,memState varchar2(10)
)

drop table Member
-- �߰���ģ��
create table Friend(
memID1 varchar2(30) constraint friend_member_fk1 references Member
,memID2 varchar2(30) constraint friend_member_fk2 references Member
,constraint id_pk primary key(memID1, memID2) 
)

drop table Friend

-- �˻��� ���
create table Place(
placeID varchar2(150) primary key
,placeName varchar2(50)
,placeLon varchar2(50)
,placeLat varchar2(50)
)

drop table Place

create table TwoPlace(
TwoPlaceSeq varchar2(50) primary key
, placeID1 varchar2(150) constraint places_fk1 references Place  
, placeID2 varchar2(150) constraint places_fk2 references Place
)
drop table TwoPlace

create sequence TwoPlaceSeq

drop sequence TwoPlaceSeq

create table Favorite(
memID varchar2(30) constraint fav_mem_fk references Member
,TwoPlaceSeq varchar2(50) constraint fav_twoPlace_fk references TwoPlace
, constraint fav_pk primary key(memID, TwoPlaceSeq)
)

drop table Favorite

select *
from Member;
select *
from Place;
select *
from TwoPlace;
