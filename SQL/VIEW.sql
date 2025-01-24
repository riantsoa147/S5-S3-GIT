CREATE OR REPLACE VIEW technician_commissions AS
SELECT 
    DATE(m.end_date) AS maintenance_date, -- Extraction de la date
    m.id AS maintenance_id,
    t.id AS technician_id,
    (COALESCE(SUM(md.sell_price - md.unit_price), 0) + COALESCE(SUM(s.price), 0)) AS total_maintenance_earnings,
    CASE WHEN (COALESCE(SUM(md.sell_price - md.unit_price), 0) + COALESCE(SUM(s.price), 0)) >= (select maximum from config where id = 1)
    THEN
    ROUND((COALESCE(SUM(md.sell_price - md.unit_price), 0) + COALESCE(SUM(s.price), 0)) * (0.01 * t.commission), 2)
    ELSE 0
    END
     AS technician_commission
FROM 
    maintenances m
LEFT JOIN 
    maintenances_details md ON m.id = md.maintenance_id
LEFT JOIN 
    services s ON md.service_id = s.id
JOIN 
    technicians t ON m.technician_id = t.id
GROUP BY 
    DATE(m.end_date), m.id, t.id -- Utilisation de la date extraite pour le GROUP BY
ORDER BY 
    m.id;

create or replace view technician_gender_commission_states as
select tc.maintenance_date, sum(tc.technician_commission), t.gender_id from technician_commissions tc left join technicians t on tc.technician_id = t.id group by t.gender_id, tc.maintenance_date;