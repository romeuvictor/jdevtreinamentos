create SEQUENCE usersequence
increment 1
minvalue 1
maxvalue 9223372036854775807
start 7;

alter table userposjava ALTER column id set default nextval('usersequence'::regclass);

select * from userposjava;