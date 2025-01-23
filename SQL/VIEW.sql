CREATE OR REPLACE VIEW technician_commissions AS
SELECT 
    DATE(m.end_date) AS maintenance_date, -- Extraction de la date
    m.id AS maintenance_id,
    t.id AS technician_id,
    (COALESCE(SUM(md.sell_price - md.unit_price), 0) + COALESCE(SUM(s.price), 0)) AS total_maintenance_earnings,
    ROUND((COALESCE(SUM(md.sell_price - md.unit_price), 0) + COALESCE(SUM(s.price), 0)) * 0.05, 2) AS technician_commission
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
