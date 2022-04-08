INSERT INTO Prod_Info(product_Id,title,total_Investing_Amount,now_Investing_Amount,now_Investing_Cnt,started_At,finished_At,state) VALUES(1,'개인신용 포트폴리오',1000000,0,0, add_months(sysdate,-12),add_months(sysdate,12),'모집중');
INSERT INTO Prod_Info(product_Id,title,total_Investing_Amount,now_Investing_Amount,now_Investing_Cnt,started_At,finished_At,state) VALUES(2,'부동산 포트폴리오',5000000,0,0, add_months(sysdate,-12),add_months(sysdate,12),'모집중');
INSERT INTO Prod_Info(product_Id,title,total_Investing_Amount,now_Investing_Amount,now_Investing_Cnt,started_At,finished_At,state) VALUES(3,'ended individual portfolio',6000000,0,0, add_months(sysdate,-12),add_months(sysdate,-5),'모집완료');
INSERT INTO Prod_Info(product_Id,title,total_Investing_Amount,now_Investing_Amount,now_Investing_Cnt,started_At,finished_At,state) VALUES(4,'ended real estate  portfolio',7000000,0,0, add_months(sysdate,-12),add_months(sysdate,-5),'모집완료');

INSERT INTO MESSAGE(kind,code,name) VALUES('B','B006','조회결과가 없습니다.');
INSERT INTO MESSAGE(kind,code,name) VALUES('B','B005','필수입력항목 확인해주세요. [1]');
INSERT INTO MESSAGE(kind,code,name) VALUES('B','B004','마감되었습니다.(sold-out) 총 : [1] 금액을 초과할 수 없습니다.');
INSERT INTO MESSAGE(kind,code,name) VALUES('B','B003','존재하지 않는 상품입니다.');
INSERT INTO MESSAGE(kind,code,name) VALUES('B','B002','판매기간 경과 된 상품입니다.');
INSERT INTO MESSAGE(kind,code,name) VALUES('B','B001','투자가능금액을 초과하였습니다. [1]');
INSERT INTO MESSAGE(kind,code,name) VALUES('B','B000','모집마감상태 입니다. 투자 불가합니다.');