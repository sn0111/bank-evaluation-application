INSERT INTO public.currencies(
	currency_id, created_by, created_date, updated_by, updated_date, currency_name, currency_uuid, description)
	VALUES (1, 1,now(),1,now(), 'MUR', gen_random_uuid(),''),
	(2, 1,now(),1,now(), 'EUR', gen_random_uuid(),''),
	(3, 1,now(),1,now(), 'USD', gen_random_uuid(),''),
	(4, 1,now(),1,now(), 'GBP', gen_random_uuid(),''),
	(5, 1,now(),1,now(), 'ZAR', gen_random_uuid(),'');

INSERT INTO public.document_types(
	document_id, created_by, created_date, updated_by, updated_date, document_name, document_uuid, description)
	VALUES (1, 1,now(),1,now(), 'Title Deed', gen_random_uuid(),''),
	(2, 1,now(),1,now(), 'National Identity Card', gen_random_uuid(),''),
	(3, 1,now(),1,now(), 'Building Permits', gen_random_uuid(),''),
	(4, 1,now(),1,now(), 'Birth Certificate', gen_random_uuid(),''),
	(5, 1,now(),1,now(), 'Marriage Certificate', gen_random_uuid(),''),
	(6, 1,now(),1,now(), 'Quotation', gen_random_uuid(),''),
	(7, 1,now(),1,now(), 'Letter of Intent', gen_random_uuid(),'');

INSERT INTO public.customers(
    customer_id, customer_uuid, customer_number, short_name, is_individual, nationality, nationality_number, nationality_description, street_address, address_line2, address_line3, town_country, post_code, country, country_code, country_code_number, dispatch_code, communication_channel, phone_number, office_phone_number, fax_number, mobile_operator_iso, mobile_operator_code, sms_number, email, created_by, created_date, updated_by, updated_date)
VALUES
    (1, 'uuid1', '1234567890', 'Satya', 'Y', 'Nati 1', 'NAT01', 'Nationality Description 1', 'Street Address 1', 'Address Line 2', 'Address Line 3', 'Town Country 1', '123456', 'Country 1', 'CC1', 'CCN1', 'DC1', 'Channel 1', '1234567890', 'Office Phone 1', 'Fax 1', 'ISO1', 'MOC1', 'SMS 1', 'email1@example.com', 1,now(),1,now()),
    (2, 'uuid2', '2345678901', 'Sairam', 'N', 'Nat 2', 'NAT02', 'Nationality Description 2', 'Street Address 2', 'Address Line 2', 'Address Line 3', 'Town Country 2', '234567', 'Country 2', 'CC2', 'CCN2', 'DC2', 'Channel 2', '2345678901', 'Office Phone 2', 'Fax 2', 'ISO2', 'MOC2', 'SMS 2', 'email2@example.com', 1,now(),1,now()),
    (3, 'uuid3', '3456789012', 'Venkat', 'Y', 'Nat3', 'NAT03', 'Nationality Description 3', 'Street Address 3', 'Address Line 2', 'Address Line 3', 'Town Country 3', '345678', 'Country 3', 'CC3', 'CCN3', 'DC3', 'Channel 3', '3456789012', 'Office Phone 3', 'Fax 3', 'ISO3', 'MOC3', 'SMS 3', 'email3@example.com', 1,now(),1,now()),
    (4, 'uuid4', '4567890123', 'Annu', 'N', 'Nat 4', 'NAT04', 'Nationality Description 4', 'Street Address 4', 'Address Line 2', 'Address Line 3', 'Town Country 4', '456789', 'Country 4', 'CC4', 'CCN4', 'DC4', 'Channel 4', '4567890123', 'Office Phone 4', 'Fax 4', 'ISO4', 'MOC4', 'SMS 4', 'email4@example.com', 1,now(),1,now()),
    (5, 'uuid5', '5678901234', 'Laxmi', 'Y', 'Nat5', 'NAT05', 'Nationality Description 5', 'Street Address 5', 'Address Line 2', 'Address Line 3', 'Town Country 5', '567890', 'Country 5', 'CC5', 'CCN5', 'DC5', 'Channel 5', '5678901234', 'Office Phone 5', 'Fax 5', 'ISO5', 'MOC5', 'SMS 5', 'email5@example.com', 1,now(),1,now()),
    (6, 'uuid6', '6789012345', 'Shekar', 'N', 'Nat 6', 'NAT06', 'Nationality Description 6', 'Street Address 6', 'Address Line 2', 'Address Line 3', 'Town Country 6', '678901', 'Country 6', 'CC6', 'CCN6', 'DC6', 'Channel 6', '6789012345', 'Office Phone 6', 'Fax 6', 'ISO6', 'MOC6', 'SMS 6', 'email6@example.com', 1,now(),1,now()),
    (7, 'uuid7', '7890123456', 'Raj', 'Y', 'Nat 7', 'NAT07', 'Nationality Description 7', 'Street Address 7', 'Address Line 2', 'Address Line 3', 'Town Country 7', '789012', 'Country 7', 'CC7', 'CCN7', 'DC7', 'Channel 7', '7890123456', 'Office Phone 7', 'Fax 7', 'ISO7', 'MOC7', 'SMS 7', 'email7@example.com', 1,now(),1,now()),
    (8, 'uuid8', '8901234567', 'Kiran', 'N', 'Nat 8', 'NAT08', 'Nationality Description 8', 'Street Address 8', 'Address Line 2', 'Address Line 3', 'Town Country 8', '890123', 'Country 8', 'CC8', 'CCN8', 'DC8', 'Channel 8', '8901234567', 'Office Phone 8', 'Fax 8', 'ISO8', 'MOC8', 'SMS 8', 'email8@example.com', 1,now(),1,now()),
    (9, 'uuid9', '9012345678', 'Nani', 'Y', 'Nat 9', 'NAT09', 'Nationality Description 9', 'Street Address 9', 'Address Line 2', 'Address Line 3', 'Town Country 9', '901234', 'Country 9', 'CC9', 'CCN9', 'DC9', 'Channel 9', '9012345678', 'Office Phone 9', 'Fax 9', 'ISO9', 'MOC9', 'SMS 9', 'email9@example.com', 1,now(),1,now());

INSERT INTO public.reference_number(
	reference_id, created_by, created_date, updated_by, updated_date, reference_name, reference_number)
	VALUES (1, 1, now(), 1, now(), 'PV', 0);